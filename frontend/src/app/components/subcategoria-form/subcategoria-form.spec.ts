import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubcategoriaForm } from './subcategoria-form';

describe('SubcategoriaForm', () => {
  let component: SubcategoriaForm;
  let fixture: ComponentFixture<SubcategoriaForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubcategoriaForm],
    }).compileComponents();

    fixture = TestBed.createComponent(SubcategoriaForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
