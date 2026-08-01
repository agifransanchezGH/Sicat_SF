import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GastronomiaeForm } from './gastronomia-form';

describe('GastronomiaeForm', () => {
  let component: GastronomiaeForm;
  let fixture: ComponentFixture<GastronomiaeForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GastronomiaeForm],
    }).compileComponents();

    fixture = TestBed.createComponent(GastronomiaeForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
