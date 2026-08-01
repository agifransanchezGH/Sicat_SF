import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ServicioTuristicoService } from '../../shared/services/servicio-turistico.service';
import { ServicioTuristico } from '../../shared/models/servicio-turistico.model';

@Component({
  selector: 'app-servicio-turistico-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './servicio-turistico-form.html',
  styleUrl: './servicio-turistico-form.css'
})
export class ServicioTuristicoFormComponent implements OnInit {

  servicio: ServicioTuristico = { idEstab: '' };
  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  subcategorias = ['Agencia de viajes', 'Transfer', 'Bus turístico', 'Guía local', 'Otro'];
  tiposServicio = ['Agencias', 'Transfers', 'Bus', 'Guías'];

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

  guardar(): void {
    if (!this.servicio.idEstab?.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    const operacion = this.modoEdicion
      ? this.servicioService.actualizar(this.servicio.idEstab, this.servicio)
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
