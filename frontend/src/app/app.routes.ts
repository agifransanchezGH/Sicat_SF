import { Routes } from '@angular/router';
import { CategoriaFormComponent } from './components/categoria-form/categoria-form';
import { CategoriaListComponent } from './components/categoria-list/categoria-list';
import { EstablecimientoFormComponent } from './components/establecimiento-form/establecimiento-form';
import { EstablecimientoListComponent } from './components/establecimiento-list/establecimiento-list';
import { AlojamientoFormComponent } from './components/alojamiento-form/alojamiento-form';
import { SubcategoriaFormComponent } from './components/subcategoria-form/subcategoria-form';
import { RelevamientoFormComponent } from './components/relevamiento-form/relevamiento-form';
import { ContactoReferenteFormComponent } from './components/contacto-referente-form/contacto-referente-form';
import { GastronomiaFormComponent } from './components/gastronomia-form/gastronomia-form';

export const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'cargar', component: CategoriaFormComponent },
  { path: 'listado', component: CategoriaListComponent },
  { path: 'establecimiento/cargar', component: EstablecimientoFormComponent },
  { path: 'establecimiento/listar', component: EstablecimientoListComponent },
  { path: 'alojamiento/cargar',     component: AlojamientoFormComponent },
  { path: 'subcategoria/cargar', component: SubcategoriaFormComponent },
  { path: 'relevamiento/cargar', component: RelevamientoFormComponent},
  { path: 'contacto-referente/cargar',     component: ContactoReferenteFormComponent },
  { path: 'gastronomia/cargar',          component: GastronomiaFormComponent },
];
