import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { TurismoNauticoDeportivo } from '../../shared/models/turismo-nautico-deportivo.model';
import { TurismoNauticoDeportivoService } from '../../shared/services/turismo-nautico-deportivo.service';

@Component({
  selector: 'app-turismo-nautico-deportivo-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './turismo-nautico-deportivo-form.html',
  styleUrls: ['./turismo-nautico-deportivo-form.css']
})
export class TurismoNauticoDeportivoFormComponent {
  turismo: TurismoNauticoDeportivo = {
    idEstab: '',
    subcategoriaNautica: '',
    tipoNautico: '',
    puertoBase: '',
    embarcacionesCantidad: undefined,
    embarcacionesTipo: '',
    serviciosAdicionales: '',
    temporadaOperativa: '',
    salidaConGuia: false,
    deportesAcuaticos: '',
    escuelaNautica: false,
    numeroAlumnos: undefined,
    zonaProtegida: '',
    habilitaciones: '',
    descripcion: '',
    observaciones: ''
  };

  mensaje: string | null = null;

  constructor(private turismoService: TurismoNauticoDeportivoService) {}

  guardar(form: NgForm): void {
    if (form.invalid) {
      this.mensaje = 'Complete los campos requeridos antes de guardar.';
      return;
    }

    this.turismoService.crear(this.turismo).subscribe({
      next: () => {
        this.mensaje = 'Registro creado correctamente.';
        form.resetForm({ salidaConGuia: false, escuelaNautica: false });
      },
      error: (error) => {
        this.mensaje = 'Error al guardar el registro: ' + error.message;
      }
    });
  }
}
