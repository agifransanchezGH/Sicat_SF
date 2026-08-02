import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PatrimonioService } from '../../shared/services/patrimonio.service';
import { Patrimonio } from '../../shared/models/patrimonio.model';

@Component({
  selector: 'app-patrimonio-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './patrimonio-form.html',
  styleUrl: './patrimonio-form.css'
})
export class PatrimonioFormComponent implements OnInit {

  private _idEstab = '';
  patrimonio: Patrimonio = { idEstab: '' };
  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  subcategorias = ['Patrimonio material', 'Patrimonio inmaterial', 'Sitio arqueológico', 'Museo histórico', 'Otro'];
  tiposPatrimonio = ['Museo', 'Monumento', 'Ruta cultural', 'Sitio memorial', 'Paisaje cultural'];

  @Input()
  set idEstab(value: string) {
    this._idEstab = value || '';
    if (this._idEstab.trim()) {
      this.patrimonio.idEstab = this._idEstab;
    }
  }

  get idEstab(): string {
    return this._idEstab;
  }

  constructor(
    private patrimonioService: PatrimonioService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.patrimonio.idEstab = idParam;
      this.cargarExistente(idParam);
    }
  }

  private cargarExistente(idEstab: string): void {
    this.patrimonioService.obtener(idEstab).subscribe({
      next: (data) => {
        this.patrimonio = data;
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
    const idEstab = (this.patrimonio.idEstab || this._idEstab).trim();
    if (!idEstab) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.patrimonio.idEstab = idEstab;
    this.cargando = true;
    this.mensaje = '';

    const operacion = this.modoEdicion
      ? this.patrimonioService.actualizar(idEstab, this.patrimonio)
      : this.patrimonioService.crear(this.patrimonio);

    operacion.subscribe({
      next: (resp) => {
        this.mensaje = this.modoEdicion
          ? `Patrimonio actualizado correctamente (${resp.idEstab})`
          : `Patrimonio guardado correctamente (${resp.idEstab})`;
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

  mostrarEspacioCultural(): boolean {
    return this.patrimonio.subcategoriaPatrimonio === 'Museo histórico' ||
           this.patrimonio.subcategoriaPatrimonio === 'Patrimonio material';
  }
}
