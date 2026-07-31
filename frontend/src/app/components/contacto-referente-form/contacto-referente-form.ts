import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ContactoReferenteService } from '../../shared/services/contacto-referente.service';
import { ContactoReferente } from '../../shared/models/contacto-referente.model';

@Component({
  selector: 'app-contacto-referente-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './contacto-referente-form.html',
  styleUrl: './contacto-referente-form.css'
})
export class ContactoReferenteFormComponent implements OnInit {

  contacto: ContactoReferente = { idEstab: '' };
  contactos: ContactoReferente[] = [];
  mensaje = '';
  exito = false;
  cargando = false;

  constructor(
    private contactoService: ContactoReferenteService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.queryParamMap.get('idEstab');
    if (idParam) {
      this.contacto.idEstab = idParam;
      this.cargarContactos(idParam);
    }
  }

  private cargarContactos(idEstab: string): void {
    this.contactoService.listarPorEstablecimiento(idEstab).subscribe({
      next: (data) => this.contactos = data,
      error: () => {}
    });
  }

  guardar(): void {
    if (!this.contacto.idEstab.trim()) {
      this.mensaje = 'El ID de establecimiento es obligatorio.';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    this.contactoService.crear(this.contacto).subscribe({
      next: (resp) => {
        this.mensaje = `Contacto guardado correctamente (ID ${resp.idRef})`;
        this.exito = true;
        this.cargando = false;
        this.contactos.push(resp);
        // Limpiar manteniendo el idEstab para cargar otro contacto
        this.contacto = { idEstab: this.contacto.idEstab };
      },
      error: (err) => {
        this.mensaje = err.error ?? 'Error al guardar el contacto.';
        this.exito = false;
        this.cargando = false;
      }
    });
  }

  eliminar(idRef: number): void {
    if (!confirm('¿Confirmar eliminación del contacto?')) return;

    this.contactoService.eliminar(idRef).subscribe({
      next: () => {
        this.contactos = this.contactos.filter(c => c.idRef !== idRef);
        this.mensaje = 'Contacto eliminado.';
        this.exito = true;
      },
      error: (err) => {
        this.mensaje = err.error ?? 'Error al eliminar el contacto.';
        this.exito = false;
      }
    });
  }
}
