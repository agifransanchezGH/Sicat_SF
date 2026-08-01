import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ServicioTuristicoService } from '../../shared/services/servicio-turistico.service';
import { ServicioTuristico } from '../../shared/models/servicio-turistico.model';

@Component({
  selector: 'app-servicio-turistico-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './servicio-turistico-list.html',
  styleUrl: './servicio-turistico-list.css'
})
export class ServicioTuristicoListComponent implements OnInit {

  servicios: ServicioTuristico[] = [];
  filtroTexto = '';

  constructor(private servicioService: ServicioTuristicoService) {}

  ngOnInit(): void {
    this.cargarTodos();
  }

  cargarTodos(): void {
    this.servicioService.listar().subscribe({ next: (data) => this.servicios = data });
  }

  buscar(): void {
    if (!this.filtroTexto.trim()) {
      this.cargarTodos();
      return;
    }
    this.servicioService.buscarPorSubcategoria(this.filtroTexto).subscribe({ next: (data) => this.servicios = data });
  }
}
