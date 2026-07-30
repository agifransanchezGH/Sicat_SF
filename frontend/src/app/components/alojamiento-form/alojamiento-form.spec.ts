import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlojamientoForm } from './alojamiento-form';

describe('AlojamientoForm', () => {
  let component: AlojamientoForm;
  let fixture: ComponentFixture<AlojamientoForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AlojamientoForm],
    }).compileComponents();

    fixture = TestBed.createComponent(AlojamientoForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
