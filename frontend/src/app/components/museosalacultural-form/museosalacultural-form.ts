import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { MuseoSalaCulturalService } from '../../shared/services/museo-sala-cultural.service';
import { MuseoSalaCultural } from '../../shared/models/museo-sala-cultural.model';

@Component({
  selector: 'app-museo-sala-cultural-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './museosalacultural-form.html',
  styleUrl: './museosalacultural-form.css'
})
export class MuseoSalaCulturalFormComponent implements OnInit {

  museo: MuseoSalaCultural = { idEstab: '' };

  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  // Valores del CHECK constraint en DB
  dominios      = ['Municipal', 'Provincial', 'Nacional', 'Privado', 'Mixto'];
  funcionamientos = ['Abierto', 'Cerrado', 'Temporario', 'En reformas'];
  tiposEntrada  = ['Gratuita', 'Con cargo', 'Entrada voluntaria', 'Mixta'];

  constructor(
    private museoService: MuseoSalaCulturalService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.museo.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.museoService.obtener(idEstab).subscribe({
      next: (data) => {
        this.museo = data;
        this.modoEdicion = true;
      },
      error: () => { this.modoEdicion = false; }
    });
  }

  guardar(): void {
    if (!this.museo.idEstab.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    const op$ = this.modoEdicion
      ? this.museoService.actualizar(this.museo.idEstab, this.museo)
      : this.museoService.crear(this.museo);

    op$.subscribe({
      next: (resp) => {
        this.mensaje = this.modoEdicion
          ? `Registro actualizado correctamente (${resp.idEstab})`
          : `Registro guardado correctamente (${resp.idEstab})`;
        this.exito = true;
        this.cargando = false;
        this.modoEdicion = true;
      },
      error: (err) => {
        this.mensaje = err.error ?? 'Error al guardar.';
        this.exito = false;
        this.cargando = false;
      }
    });
  }
}