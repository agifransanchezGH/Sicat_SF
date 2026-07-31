import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactoReferenteForm } from './contacto-referente-form';

describe('ContactoReferenteForm', () => {
  let component: ContactoReferenteForm;
  let fixture: ComponentFixture<ContactoReferenteForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactoReferenteForm],
    }).compileComponents();

    fixture = TestBed.createComponent(ContactoReferenteForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
