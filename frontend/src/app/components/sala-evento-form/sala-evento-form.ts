import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SalaEventoService } from '../../shared/services/sala-evento.service';
import { SalaEvento } from '../../shared/models/sala-evento.model';

@Component({
  selector: 'app-sala-evento-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sala-evento-form.html',
  styleUrl: './sala-evento-form.css'
})
export class SalaEventoFormComponent implements OnInit {

  private _idEstab = '';
  salaEvento: SalaEvento = { idEstab: '' };
  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  @Input()
  set idEstab(value: string) {
    this._idEstab = value || '';
    if (this._idEstab.trim()) {
      this.salaEvento.idEstab = this._idEstab;
    }
  }

  get idEstab(): string {
    return this._idEstab;
  }

  constructor(
    private salaEventoService: SalaEventoService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.salaEvento.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.salaEventoService.obtener(idEstab).subscribe({
      next: (data) => {
        this.salaEvento = data;
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
    const idEstab = (this.salaEvento.idEstab || this._idEstab).trim();
    if (!idEstab) {
      this.mensaje = 'El ID de sala evento es obligatorio.';
      this.exito = false;
      return;
    }

    this.salaEvento.idEstab = idEstab;
    this.cargando = true;
    this.mensaje = '';

    const operacion = this.modoEdicion
      ? this.salaEventoService.actualizar(idEstab, this.salaEvento)
      : this.salaEventoService.crear(this.salaEvento);

    operacion.subscribe({
      next: (resp) => {
        this.mensaje = this.modoEdicion
          ? `Sala de evento actualizada correctamente (${resp.idEstab})`
          : `Sala de evento guardada correctamente (${resp.idEstab})`;
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
