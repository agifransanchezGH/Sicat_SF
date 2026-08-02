import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CategoriaService } from '../../shared/services/categoria.service';
import { Categoria } from '../../shared/models/categoria.model';
import { CommonModule } from '@angular/common';
import { FormShellComponent } from '../../shared/components/form-shell/form-shell';
@Component({
  selector: 'app-categoria-form',
  standalone: true,
  imports: [FormsModule, CommonModule, FormShellComponent],
  templateUrl: './categoria-form.html',
  styleUrl: './categoria-form.css'
})
export class CategoriaFormComponent {
  categoria: Categoria = { prefijo: '', nombreCategoria: '' };
  mensaje = '';

  constructor(private categoriaService: CategoriaService) {}

  guardar(): void {
    this.categoriaService.crear(this.categoria).subscribe({
      next: (respuesta) => {
        this.mensaje = `Categoría guardada con ID ${respuesta.idCat}`;
        this.categoria = { prefijo: '', nombreCategoria: '' }; // limpiar formulario
      },
      error: (err) => {
        this.mensaje = 'Error al guardar la categoría';
        console.error(err);
      }
    });
  }
}