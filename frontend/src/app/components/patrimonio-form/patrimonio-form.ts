import { Component, OnInit } from '@angular/core';
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

  patrimonio: Patrimonio = { idEstab: '' };
  mensaje = '';
  exito = false;
  cargando = false;
  modoEdicion = false;

  subcategorias = ['Patrimonio material', 'Patrimonio inmaterial', 'Sitio arqueológico', 'Museo histórico', 'Otro'];
  tiposPatrimonio = ['Museo', 'Monumento', 'Ruta cultural', 'Sitio memorial', 'Paisaje cultural'];

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

  guardar(): void {
    if (!this.patrimonio.idEstab?.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    const operacion = this.modoEdicion
      ? this.patrimonioService.actualizar(this.patrimonio.idEstab, this.patrimonio)
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
