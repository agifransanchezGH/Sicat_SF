import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ServicioTuristicoService } from '../../shared/services/servicio-turistico.service';
import { ServicioTuristico } from '../../shared/models/servicio-turistico.model';
import { FormShellComponent } from '../../shared/components/form-shell/form-shell';

@Component({
  selector: 'app-servicio-turistico-form',
  standalone: true,
  imports: [CommonModule, FormsModule, FormShellComponent],
  templateUrl: './servicio-turistico-form.html',
  styleUrl: './servicio-turistico-form.css'
})
export class ServicioTuristicoFormComponent implements OnInit {

  private _idEstab = '';
  servicio: ServicioTuristico = { idEstab: '' };
  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  subcategorias = ['Agencia de viajes', 'Transfer', 'Bus turístico', 'Guía local', 'Otro'];
  tiposServicio = ['Agencias', 'Transfers', 'Bus', 'Guías'];

  @Input()
  set idEstab(value: string) {
    this._idEstab = value || '';
    if (this._idEstab.trim()) {
      this.servicio.idEstab = this._idEstab;
    }
  }

  get idEstab(): string {
    return this._idEstab;
  }

  constructor(
    private servicioService: ServicioTuristicoService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.servicio.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.servicioService.obtener(idEstab).subscribe({
      next: (data) => {
        this.servicio = data;
        this.modoEdicion = true;
      },
      error: () => {
        this.modoEdicion = false;
      }
    });
  }

  setIdEstab(idEstab: string): void {
    this.idEstab = idEstab;
  }

  guardar(): void {
    const idEstab = (this.servicio.idEstab || this._idEstab).trim();
    if (!idEstab) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.servicio.idEstab = idEstab;
    this.cargando = true;
    this.mensaje = '';

    const operacion = this.modoEdicion
      ? this.servicioService.actualizar(idEstab, this.servicio)
      : this.servicioService.crear(this.servicio);

    operacion.subscribe({
      next: (resp) => {
        this.mensaje = this.modoEdicion
          ? `Servicio actualizado correctamente (${resp.idEstab})`
          : `Servicio guardado correctamente (${resp.idEstab})`;
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

  necesitaRamal(): boolean {
    return this.servicio.subcategoriaServicio === 'Bus turístico';
  }

  necesitaVehiculo(): boolean {
    return this.servicio.subcategoriaServicio === 'Transfer' ||
           this.servicio.subcategoriaServicio === 'Bus turístico';
  }
}
