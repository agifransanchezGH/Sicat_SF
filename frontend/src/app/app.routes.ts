import { Routes } from '@angular/router';
import { CategoriaFormComponent } from './components/categoria-form/categoria-form';
import { CategoriaListComponent } from './components/categoria-list/categoria-list';
import { EstablecimientoFormComponent } from './components/establecimiento-form/establecimiento-form';
import { EstablecimientoListComponent } from './components/establecimiento-list/establecimiento-list';
import { AlojamientoFormComponent } from './components/alojamiento-form/alojamiento-form';

export const routes: Routes = [
  { path: '', redirectTo: 'listado', pathMatch: 'full' },
  { path: 'cargar', component: CategoriaFormComponent },
  { path: 'listado', component: CategoriaListComponent },
  { path: 'establecimiento/cargar', component: EstablecimientoFormComponent },
  { path: 'establecimiento/listar', component: EstablecimientoListComponent },
  { path: 'alojamiento/cargar',     component: AlojamientoFormComponent }
];
