import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TurismoNauticoDeportivo } from '../../shared/models/turismo-nautico-deportivo.model';
import { TurismoNauticoDeportivoService } from '../../shared/services/turismo-nautico-deportivo.service';
import { FormShellComponent } from '../../shared/components/form-shell/form-shell';

@Component({
  selector: 'app-turismo-nautico-deportivo-form',
  standalone: true,
  imports: [CommonModule, FormsModule, FormShellComponent],
  templateUrl: './turismo-nautico-deportivo-form.html',
  styleUrls: ['./turismo-nautico-deportivo-form.css']
})
export class TurismoNauticoDeportivoFormComponent {
  private _idEstab = '';

  turismo: TurismoNauticoDeportivo = {
    idEstab: '',
    subcategoriaNau: '',
    funcionamiento: '',
    deportes: '',
    instalaciones: '',
    serviciosClub: '',
    requisitosAdmision: '',
    deportesNauticos: '',
    cantEmbarcaciones: undefined,
    serviciosNauticos: '',
    serviciosActivNau: '',
    cantEquipoNautico: undefined,
    serviciosGuarderia: '',
    actividadesRecreacion: '',
    cantAmarres: undefined,
    serviciosParador: '',
    accesoPlaya: '',
    temporada: '',
    productosPesca: '',
    tipoPesca: ''
  };

  mensaje: string | null = null;

  @Input()
  set idEstab(value: string) {
    this._idEstab = value || '';
    if (this._idEstab.trim()) {
      this.turismo.idEstab = this._idEstab;
    }
  }

  get idEstab(): string {
    return this._idEstab;
  }

  constructor(private turismoService: TurismoNauticoDeportivoService) {}

  setIdEstab(idEstab: string): void {
    this.idEstab = idEstab;
  }

  guardar(): void {
    this.turismoService.crear(this.turismo).subscribe({
      next: () => {
        this.mensaje = 'Registro creado correctamente.';
      },
      error: (error) => {
        this.mensaje = 'Error al guardar el registro: ' + error.message;
      }
    });
  }
}
