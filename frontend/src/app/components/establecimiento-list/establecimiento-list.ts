import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EstablecimientoService } from '../../shared/services/establecimiento.service';
import { Establecimiento } from '../../shared/models/establecimiento.model';

@Component({
  selector: 'app-establecimiento-list',
  imports: [CommonModule, FormsModule],
  templateUrl: './establecimiento-list.html',
  styleUrl: './establecimiento-list.css',
})

export class EstablecimientoListComponent implements OnInit {
  establecimiento: Establecimiento[] = [];
  textoBusqueda = '';

  constructor(private establecimientoService: EstablecimientoService) {}

  ngOnInit(): void {
    this.cargarTodos();
  }

  cargarTodos(): void {
    this.establecimientoService.listar().subscribe({
      next: (data) => this.establecimiento = data
    });
  }

  buscar(): void {
    if (!this.textoBusqueda.trim()) {
      this.cargarTodos();
      return;
    }
    this.establecimientoService.buscar(this.textoBusqueda).subscribe({
      next: (data) => this.establecimiento = data
    });
  }
}