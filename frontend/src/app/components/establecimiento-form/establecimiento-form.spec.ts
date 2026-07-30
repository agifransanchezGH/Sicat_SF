import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstablecimientoFormComponent } from './establecimiento-form';

describe('EstablecimientoForm', () => {
  let component: EstablecimientoFormComponent;
  let fixture: ComponentFixture<EstablecimientoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EstablecimientoFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EstablecimientoFormComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
