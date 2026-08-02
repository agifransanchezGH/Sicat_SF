import { Component, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgSwitch, NgSwitchCase, NgSwitchDefault } from '@angular/common';
import { EstablecimientoService } from '../../shared/services/establecimiento.service';
import { CategoriaService } from '../../shared/services/categoria.service';
import { Establecimiento } from '../../shared/models/establecimiento.model';
import { Categoria } from '../../shared/models/categoria.model';
import { Subcategoria } from '../../shared/models/subcategoria.model';
import { SubcategoriaService } from '../../shared/services/subcategoria.service';
import { AlojamientoFormComponent } from '../alojamiento-form/alojamiento-form';
import { GastronomiaFormComponent } from '../gastronomia-form/gastronomia-form';
import { MuseoSalaCulturalFormComponent } from '../museosalacultural-form/museosalacultural-form';
import { SalaEventoFormComponent } from '../sala-evento-form/sala-evento-form';
import { RecreacionDiversionFormComponent } from '../recreacion-diversion-form/recreacion-diversion-form';
import { PatrimonioFormComponent } from '../patrimonio-form/patrimonio-form';
import { ServicioTuristicoFormComponent } from '../servicio-turistico-form/servicio-turistico-form';
import { TurismoNauticoDeportivoFormComponent } from '../turismo-nautico-deportivo-form/turismo-nautico-deportivo-form';



@Component({
  selector: 'app-establecimiento-form',
  standalone: true,
  imports: [
    FormsModule, 
    CommonModule, 
    AlojamientoFormComponent,
    GastronomiaFormComponent,
    MuseoSalaCulturalFormComponent,
    SalaEventoFormComponent,
    RecreacionDiversionFormComponent,
    PatrimonioFormComponent,
    ServicioTuristicoFormComponent,
    TurismoNauticoDeportivoFormComponent,
    NgSwitch,NgSwitchCase,
    NgSwitchDefault
  ],
  templateUrl: './establecimiento-form.html',
  styleUrl: './establecimiento-form.css'
})
export class EstablecimientoFormComponent implements OnInit {

  establecimiento: Establecimiento = {
    idCat: -1,
    nombre: '',
    estado: 'Activo'
  };

  categorias: Categoria[] = [];
  mensaje = '';
  exito = false;
  cargando = false;
  @ViewChild(AlojamientoFormComponent) alojamientoForm?: AlojamientoFormComponent;
  @ViewChild(GastronomiaFormComponent) gastronomiaForm?: GastronomiaFormComponent;
  @ViewChild(MuseoSalaCulturalFormComponent) museoSalaCulturalForm?: MuseoSalaCulturalFormComponent;
  @ViewChild(SalaEventoFormComponent) salaEventoForm?: SalaEventoFormComponent;
  @ViewChild(RecreacionDiversionFormComponent) recreacionDiversionForm?: RecreacionDiversionFormComponent;
  @ViewChild(PatrimonioFormComponent) patrimonioForm?: PatrimonioFormComponent;
  @ViewChild(ServicioTuristicoFormComponent) servicioTuristicoForm?: ServicioTuristicoFormComponent;
  @ViewChild(TurismoNauticoDeportivoFormComponent) turismoNauticoForm?: TurismoNauticoDeportivoFormComponent;

  constructor(
    private establecimientoService: EstablecimientoService,
    private categoriaService: CategoriaService,
    private subcategoriaService: SubcategoriaService
  ) {}

  subcategorias: Subcategoria[] = [];

  ngOnInit(): void {
    this.categoriaService.listar().subscribe({
      next: (data) => this.categorias = data,
      error: () => this.mensaje = 'No se pudieron cargar las categorías'
    });
  }

  onCategoriaChange(): void {
  if (this.establecimiento.idCat !== -1) {
    this.subcategoriaService
      .listarPorCategoria(this.establecimiento.idCat)
      .subscribe({ next: (data) => this.subcategorias = data });
  } else {
    this.subcategorias = [];
  }
}
  guardar(): void {
    if (this.establecimiento.idCat === -1 || !this.establecimiento.nombre.trim()) {
      this.mensaje = 'Categoría y nombre son obligatorios';
      this.exito = false;
      return;
    }

    this.cargando = true;
    this.mensaje = '';

    this.establecimientoService.crear(this.establecimiento).subscribe({
      next: (respuesta) => {
        const idEstab = respuesta.idEstab;
        this.mensaje = idEstab
          ? `Establecimiento guardado con ID ${idEstab}`
          : 'Establecimiento guardado correctamente';
        this.exito = true;
        this.cargando = false;
        if (idEstab) {
          this.procesarDetalleRelacionado(idEstab);
        }
        this.limpiarFormulario();
      },
      error: (err) => {
        this.mensaje = 'Error al guardar el establecimiento';
        this.exito = false;
        this.cargando = false;
        console.error(err);
      }
    });
  }

  private procesarDetalleRelacionado(idEstab: string): void {
    switch (this.establecimiento.idCat) {
      case 1:
      case 2:
        this.alojamientoForm?.setIdEstab(idEstab);
        this.alojamientoForm?.guardar();
        break;
      case 3:
        this.gastronomiaForm?.setIdEstab(idEstab);
        this.gastronomiaForm?.guardar();
        break;
      case 4:
        this.museoSalaCulturalForm?.setIdEstab(idEstab);
        this.museoSalaCulturalForm?.guardar();
        break;
      case 5:
        this.salaEventoForm?.setIdEstab(idEstab);
        this.salaEventoForm?.guardar();
        break;
      case 6:
        this.recreacionDiversionForm?.setIdEstab(idEstab);
        this.recreacionDiversionForm?.guardar();
        break;
      case 7:
        this.patrimonioForm?.setIdEstab(idEstab);
        this.patrimonioForm?.guardar();
        break;
      case 8:
        this.servicioTuristicoForm?.setIdEstab(idEstab);
        this.servicioTuristicoForm?.guardar();
        break;
      case 9:
        this.turismoNauticoForm?.setIdEstab(idEstab);
        this.turismoNauticoForm?.guardar();
        break;
      default:
        break;
    }
  }

  private limpiarFormulario(): void {
  this.establecimiento = { idCat: -1, nombre: '', estado: 'Activo' };
  this.subcategorias = [];
}

}
