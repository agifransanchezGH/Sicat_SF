import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { MatTabsModule } from '@angular/material/tabs';
import { EstablecimientoFormComponent } from './components/establecimiento-form/establecimiento-form';
import { EstablecimientoListComponent } from './components/establecimiento-list/establecimiento-list';
import { AlojamientoFormComponent } from './components/alojamiento-form/alojamiento-form';
import { CategoriaFormComponent } from './components/categoria-form/categoria-form';
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
    CategoriaFormComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class AppComponent {}