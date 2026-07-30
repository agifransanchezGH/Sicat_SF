import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstablecimientoListComponent } from './establecimiento-list';

describe('EstablecimientoList', () => {
  let component: EstablecimientoListComponent;
  let fixture: ComponentFixture<EstablecimientoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EstablecimientoListComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EstablecimientoListComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
