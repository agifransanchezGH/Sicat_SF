import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PatrimonioService } from '../../shared/services/patrimonio.service';
import { Patrimonio } from '../../shared/models/patrimonio.model';

@Component({
  selector: 'app-patrimonio-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './patrimonio-list.html',
  styleUrl: './patrimonio-list.css'
})
export class PatrimonioListComponent implements OnInit {

  patrimonios: Patrimonio[] = [];
  filtroTexto = '';

  constructor(private patrimonioService: PatrimonioService) {}

  ngOnInit(): void {
    this.cargarTodos();
  }

  cargarTodos(): void {
    this.patrimonioService.listar().subscribe({
      next: (data) => this.patrimonios = data
    });
  }

  buscar(): void {
    if (!this.filtroTexto.trim()) {
      this.cargarTodos();
      return;
    }

    this.patrimonioService.buscarPorSubcategoria(this.filtroTexto).subscribe({
      next: (data) => this.patrimonios = data
    });
  }
}
