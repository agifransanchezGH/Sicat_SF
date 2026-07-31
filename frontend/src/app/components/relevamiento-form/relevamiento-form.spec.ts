import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RelevamientoForm } from './relevamiento-form';

describe('RelevamientoForm', () => {
  let component: RelevamientoForm;
  let fixture: ComponentFixture<RelevamientoForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RelevamientoForm],
    }).compileComponents();

    fixture = TestBed.createComponent(RelevamientoForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
