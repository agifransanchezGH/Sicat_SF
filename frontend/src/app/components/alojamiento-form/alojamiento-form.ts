import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { AlojamientoService } from '../../shared/services/alojamiento.service';
import { Alojamiento } from '../../shared/models/alojamiento.model';

@Component({
  selector: 'app-alojamiento-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './alojamiento-form.html',
  styleUrl: './alojamiento-form.css'
})
export class AlojamientoFormComponent implements OnInit {

  private _idEstab = '';
  alojamiento: Alojamiento = { idEstab: '' };

  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  @Input()
  set idEstab(value: string) {
    this._idEstab = value || '';
    if (this._idEstab.trim()) {
      this.alojamiento.idEstab = this._idEstab;
    }
  }

  get idEstab(): string {
    return this._idEstab;
  }

  constructor(
    private alojamientoService: AlojamientoService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Si llega un idEstab por query param (?idEstab=SICAT-HOT-00001),
    // se pre-carga el formulario con los datos existentes para edición
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.alojamiento.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.alojamientoService.obtener(idEstab).subscribe({
      next: (data) => {
        this.alojamiento = data;
        this.modoEdicion = true;
      },
      // Si no existe registro previo, quedamos en modo creación con el ID pre-cargado
      error: () => { this.modoEdicion = false; }
    });
  }

  setIdEstab(idEstab: string): void {
    this.idEstab = idEstab;
  }

  guardar(): void {
    const idEstab = (this.alojamiento.idEstab || this._idEstab).trim();
    if (!idEstab) {
      this.mensaje = 'El ID de establecimiento es obligatorio';
      this.exito = false;
      return;
    }

    this.alojamiento.idEstab = idEstab;
    this.cargando = true;
    this.mensaje = '';

    const operacion$ = this.modoEdicion
      ? this.alojamientoService.actualizar(idEstab, this.alojamiento)
      : this.alojamientoService.crear(this.alojamiento);

    operacion$.subscribe({
      next: (respuesta) => {
        this.mensaje = this.modoEdicion
          ? `Alojamiento actualizado correctamente (${respuesta.idEstab})`
          : `Alojamiento guardado correctamente (${respuesta.idEstab})`;
        this.exito = true;
        this.cargando = false;
        this.modoEdicion = true; // tras guardar, siguiente acción sería editar
      },
      error: (err) => {
        this.mensaje = err.error ?? 'Error al guardar el alojamiento';
        this.exito = false;
        this.cargando = false;
      }
    });
  }
}