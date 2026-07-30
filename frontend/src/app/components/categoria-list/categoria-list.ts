import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoriaService } from '../../shared/services/categoria.service';
import { Categoria } from '../../shared/models/categoria.model';

@Component({
  selector: 'app-categoria-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './categoria-list.html',
  styleUrl: './categoria-list.css'
})
export class CategoriaListComponent implements OnInit {
  categorias: Categoria[] = [];
  textoBusqueda = '';

  constructor(private categoriaService: CategoriaService) {}

  ngOnInit(): void {
    this.cargarTodos();
  }

  cargarTodos(): void {
    this.categoriaService.listar().subscribe({
      next: (data) => this.categorias = data
    });
  }

  buscar(): void {
    if (!this.textoBusqueda.trim()) {
      this.cargarTodos();
      return;
    }
    this.categoriaService.buscar(this.textoBusqueda).subscribe({
      next: (data) => this.categorias = data
    });
  }
}