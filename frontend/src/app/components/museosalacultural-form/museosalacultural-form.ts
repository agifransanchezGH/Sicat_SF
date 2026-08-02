import { Component, Input, OnInit } from '@angular/core';
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

  private _idEstab = '';
  museo: MuseoSalaCultural = { idEstab: '' };

  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  // Valores del CHECK constraint en DB
  dominios      = ['Municipal', 'Provincial', 'Nacional', 'Privado', 'Mixto'];
  funcionamientos = ['Abierto', 'Cerrado', 'Temporario', 'En reformas'];
  tiposEntrada  = ['Gratuita', 'Con cargo', 'Entrada voluntaria', 'Mixta'];

  @Input()
  set idEstab(value: string) {
    this._idEstab = value || '';
    if (this._idEstab.trim()) {
      this.museo.idEstab = this._idEstab;
    }
  }

  get idEstab(): string {
    return this._idEstab;
  }

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

  setIdEstab(idEstab: string): void {
    this.idEstab = idEstab;
  }

  guardar(): void {
    const idEstab = (this.museo.idEstab || this._idEstab).trim();
    if (!idEstab) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.museo.idEstab = idEstab;
    this.cargando = true;
    this.mensaje = '';

    const op$ = this.modoEdicion
      ? this.museoService.actualizar(idEstab, this.museo)
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