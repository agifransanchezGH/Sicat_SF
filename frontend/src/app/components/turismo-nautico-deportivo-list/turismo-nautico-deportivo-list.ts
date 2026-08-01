import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TurismoNauticoDeportivo } from '../../shared/models/turismo-nautico-deportivo.model';
import { TurismoNauticoDeportivoService } from '../../shared/services/turismo-nautico-deportivo.service';

@Component({
  selector: 'app-turismo-nautico-deportivo-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './turismo-nautico-deportivo-list.html',
  styleUrls: ['./turismo-nautico-deportivo-list.css']
})
export class TurismoNauticoDeportivoListComponent implements OnInit {
  turismos: TurismoNauticoDeportivo[] = [];
  mensaje: string | null = null;

  constructor(private turismoService: TurismoNauticoDeportivoService) {}

  ngOnInit(): void {
    this.cargarTurismos();
  }

  cargarTurismos(): void {
    this.turismoService.listarTodos().subscribe({
      next: (data) => {
        this.turismos = data;
      },
      error: (error) => {
        this.mensaje = 'Error al cargar los registros: ' + error.message;
      }
    });
  }
}
