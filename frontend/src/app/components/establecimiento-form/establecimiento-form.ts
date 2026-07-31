import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgSwitch, NgSwitchCase, NgSwitchDefault } from '@angular/common';
import { EstablecimientoService } from '../../shared/services/establecimiento.service';
import { CategoriaService } from '../../shared/services/categoria.service';
import { Establecimiento } from '../../shared/models/establecimiento.model';
import { Categoria } from '../../shared/models/categoria.model';
import { Subcategoria } from '../../shared/models/subcategoria.model';
import { SubcategoriaService } from '../../shared/services/subcategoria.service';
import { AlojamientoFormComponent } from '../alojamiento-form/alojamiento-form';

@Component({
  selector: 'app-establecimiento-form',
  standalone: true,
  imports: [FormsModule, CommonModule, AlojamientoFormComponent, NgSwitch,NgSwitchCase,NgSwitchDefault],
  templateUrl: './establecimiento-form.html',
  styleUrl: './establecimiento-form.css'
})
export class EstablecimientoFormComponent implements OnInit {

  establecimiento: Establecimiento = {
    idCat: -1,
    nombre: '',
    estado: 'Activo'
  };

  categorias: Categoria[] = [];
  mensaje = '';
  exito = false;
  cargando = false;
  constructor(
    private establecimientoService: EstablecimientoService,
    private categoriaService: CategoriaService,
    private subcategoriaService: SubcategoriaService
  ) {}

  subcategorias: Subcategoria[] = [];

  ngOnInit(): void {
    this.categoriaService.listar().subscribe({
      next: (data) => this.categorias = data,
      error: () => this.mensaje = 'No se pudieron cargar las categorías'
    });
  }

  onCategoriaChange(): void {
  if (this.establecimiento.idCat !== -1) {
    this.subcategoriaService
      .listarPorCategoria(this.establecimiento.idCat)
      .subscribe({ next: (data) => this.subcategorias = data });
  } else {
    this.subcategorias = [];
  }
}
  guardar(): void {
    if (this.establecimiento.idCat === -1 || !this.establecimiento.nombre.trim()) {
      this.mensaje = 'Categoría y nombre son obligatorios';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    this.establecimientoService.crear(this.establecimiento).subscribe({
      next: (respuesta) => {
        this.mensaje = `Establecimiento guardado con ID ${respuesta.idEstab}`;
        this.exito = true;
        this.cargando = false;
        this.limpiarFormulario();
      },
      error: (err) => {
        this.mensaje = 'Error al guardar el establecimiento';
        this.exito = false;
        this.cargando = false;
        console.error(err);
      }
    });
  }

  private limpiarFormulario(): void {
  this.establecimiento = { idCat: -1, nombre: '', estado: 'Activo' };
  this.subcategorias = [];
}

}
