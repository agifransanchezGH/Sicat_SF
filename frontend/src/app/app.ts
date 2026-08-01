import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
import { EstablecimientoFormComponent } from './components/establecimiento-form/establecimiento-form';
import { EstablecimientoListComponent } from './components/establecimiento-list/establecimiento-list';
import { AlojamientoFormComponent } from './components/alojamiento-form/alojamiento-form';
import { CategoriaFormComponent } from './components/categoria-form/categoria-form';
import { CategoriaListComponent } from './components/categoria-list/categoria-list';
import { GastronomiaFormComponent } from './components/gastronomia-form/gastronomia-form';
import { GastronomiaeList } from './components/gastronomia-list/gastronomia-list';
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet, 
    RouterLink,
    MatTabsModule,
    EstablecimientoFormComponent,
    EstablecimientoListComponent,
    AlojamientoFormComponent,
    CategoriaFormComponent,
    CategoriaListComponent,
    GastronomiaFormComponent,
    GastronomiaeList
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {}