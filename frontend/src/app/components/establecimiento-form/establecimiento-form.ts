import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EstablecimientoService } from '../../shared/services/establecimiento.service';
import { CategoriaService } from '../../shared/services/categoria.service';
import { Establecimiento } from '../../shared/models/establecimiento.model';
import { Categoria } from '../../shared/models/categoria.model';

@Component({
  selector: 'app-establecimiento-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './establecimiento-form.html',
  styleUrl: './establecimiento-form.css'
})
export class EstablecimientoFormComponent implements OnInit {

  establecimiento: Establecimiento = {
    idCat: 0,
    nombre: '',
    estado: 'Activo'
  };

  categorias: Categoria[] = [];
  mensaje = '';
  exito = false;
  cargando = false;

  constructor(
    private establecimientoService: EstablecimientoService,
    private categoriaService: CategoriaService
  ) {}

  ngOnInit(): void {
    this.categoriaService.listar().subscribe({
      next: (data) => this.categorias = data,
      error: () => this.mensaje = 'No se pudieron cargar las categorías'
    });
  }

  guardar(): void {
    if (!this.establecimiento.idCat || !this.establecimiento.nombre.trim()) {
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
    this.establecimiento = { idCat: 0, nombre: '', estado: 'Activo' };
  }
}
