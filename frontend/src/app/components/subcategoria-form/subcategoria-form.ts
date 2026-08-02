import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SubcategoriaService } from '../../shared/services/subcategoria.service';
import { CategoriaService } from '../../shared/services/categoria.service';
import { Subcategoria } from '../../shared/models/subcategoria.model';
import { Categoria } from '../../shared/models/categoria.model';
import { FormShellComponent } from '../../shared/components/form-shell/form-shell';

@Component({
  selector: 'app-subcategoria-form',
  standalone: true,
  imports: [FormsModule, CommonModule, FormShellComponent],
  templateUrl: './subcategoria-form.html',
  styleUrl: './subcategoria-form.css'
})
export class SubcategoriaFormComponent implements OnInit {

  subcategoria: Subcategoria = { idCat: 0, nombreSubcat: '' };
  categorias: Categoria[] = [];
  mensaje = '';
  exito = false;
  cargando = false;

  constructor(
    private subcategoriaService: SubcategoriaService,
    private categoriaService: CategoriaService
  ) {}

  ngOnInit(): void {
    this.categoriaService.listar().subscribe({
      next: (data) => this.categorias = data,
      error: () => { this.mensaje = 'No se pudieron cargar las categorías.'; }
    });
  }

  guardar(): void {
    if (!this.subcategoria.idCat || !this.subcategoria.nombreSubcat.trim()) {
      this.mensaje = 'Categoría y nombre son obligatorios.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    this.subcategoriaService.crear(this.subcategoria).subscribe({
      next: (resp) => {
        this.mensaje = `Subcategoría guardada con ID ${resp.idSubcat}`;
        this.exito = true;
        this.cargando = false;
        this.subcategoria = { idCat: 0, nombreSubcat: '' };
      },
      error: (err) => {
        this.mensaje = err.error ?? 'Error al guardar la subcategoría.';
        this.exito = false;
        this.cargando = false;
      }
    });
  }
}