import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RecreacionDiversionService } from '../../shared/services/recreacion-diversion.service';
import { RecreacionDiversion } from '../../shared/models/recreacion-diversion.model';

@Component({
  selector: 'app-recreacion-diversion-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './recreacion-diversion-form.html',
  styleUrl: './recreacion-diversion-form.css'
})
export class RecreacionDiversionFormComponent implements OnInit {

  recreacion: RecreacionDiversion = { idEstab: '' };
  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  subcategorias = ['Parque acuático', 'Complejo deportivo', 'Club social', 'Centro de entretenimiento', 'Otro'];
  servicios = ['Acuático', 'Deportivo', 'Familiar', 'Infantil', 'Nocturno'];

  constructor(
    private service: RecreacionDiversionService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.recreacion.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.service.obtener(idEstab).subscribe({
      next: (data) => {
        this.recreacion = data;
        this.modoEdicion = true;
      },
      error: () => {
        this.modoEdicion = false;
      }
    });
  }

  guardar(): void {
    if (!this.recreacion.idEstab?.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    const operacion = this.modoEdicion
      ? this.service.actualizar(this.recreacion.idEstab, this.recreacion)
      : this.service.crear(this.recreacion);

    operacion.subscribe({
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

  esAcuatica(): boolean {
    return this.recreacion.tipoServicio?.toLowerCase().includes('acuático') ||
      this.recreacion.subcategoriaRecreacion === 'Parque acuático';
  }

  esDeportiva(): boolean {
    return this.recreacion.tipoServicio?.toLowerCase().includes('deportivo') ||
      this.recreacion.subcategoriaRecreacion === 'Complejo deportivo';
  }

  esDiversion(): boolean {
    return ['Centro de entretenimiento', 'Club social', 'Otro'].includes(this.recreacion.subcategoriaRecreacion || '');
  }
}
