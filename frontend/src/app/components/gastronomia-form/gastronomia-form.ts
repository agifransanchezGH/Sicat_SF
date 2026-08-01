import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { GastronomiaService } from '../../shared/services/gastronomia.service';
import { Gastronomia } from '../../shared/models/gastronomia.model';

@Component({
  selector: 'app-gastronomia-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './gastronomia-form.html',
  styleUrl: './gastronomia-form.css'
})
export class GastronomiaFormComponent implements OnInit {

  gastronomia: Gastronomia = { idEstab: '' };

  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  constructor(
    private gastronomiaService: GastronomiaService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.gastronomia.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.gastronomiaService.obtener(idEstab).subscribe({
      next: (data) => {
        this.gastronomia = data;
        this.modoEdicion = true;
      },
      error: () => { this.modoEdicion = false; }
    });
  }

  guardar(): void {
    if (!this.gastronomia.idEstab.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    const op$ = this.modoEdicion
      ? this.gastronomiaService.actualizar(this.gastronomia.idEstab, this.gastronomia)
      : this.gastronomiaService.crear(this.gastronomia);

    op$.subscribe({
      next: (resp) => {
        this.mensaje = this.modoEdicion
          ? `Gastronomía actualizada correctamente (${resp.idEstab})`
          : `Gastronomía guardada correctamente (${resp.idEstab})`;
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