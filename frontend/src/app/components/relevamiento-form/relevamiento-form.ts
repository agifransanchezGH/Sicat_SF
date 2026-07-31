import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { RelevamientoService } from '../../shared/services/relevamiento.service';
import { Relevamiento } from '../../shared/models/relevamiento.model';

@Component({
  selector: 'app-relevamiento-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './relevamiento-form.html',
  styleUrl: './relevamiento-form.css'
})
export class RelevamientoFormComponent implements OnInit {

  relevamiento: Relevamiento = {
    idEstab: '',
    estadoCarga: 'Completo'
  };

  historial: Relevamiento[] = [];
  mensaje = '';
  exito = false;
  cargando = false;

  // Alineados con el CHECK del schema
  estados = ['Completo', 'Incompleto', 'En revisión', 'Con observaciones'];

  constructor(
    private relevamientoService: RelevamientoService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.relevamiento.idEstab = idParam;
      this.cargarHistorial(idParam);
    }
    this.relevamiento.fechaRelevamiento = new Date().toISOString().split('T')[0];
  }

  private cargarHistorial(idEstab: string): void {
    this.relevamientoService.listarPorEstablecimiento(idEstab).subscribe({
      next: (data) => this.historial = data,
      error: () => {}
    });
  }

  guardar(): void {
    if (!this.relevamiento.idEstab.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    this.relevamientoService.registrar(this.relevamiento).subscribe({
      next: (resp) => {
        this.mensaje = `Relevamiento registrado con ID ${resp.idRelev}`;
        this.exito = true;
        this.cargando = false;
        this.historial.unshift(resp);
        this.relevamiento = {
          idEstab: this.relevamiento.idEstab,
          estadoCarga: 'Completo',
          fechaRelevamiento: new Date().toISOString().split('T')[0]
        };
      },
      error: (err) => {
        this.mensaje = err.error ?? 'Error al registrar el relevamiento.';
        this.exito = false;
        this.cargando = false;
      }
    });
  }
}