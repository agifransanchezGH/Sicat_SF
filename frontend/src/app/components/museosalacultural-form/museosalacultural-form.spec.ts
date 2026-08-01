import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuseosalaculturalForm } from './museosalacultural-form';

describe('MuseosalaculturalForm', () => {
  let component: MuseosalaculturalForm;
  let fixture: ComponentFixture<MuseosalaculturalForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MuseosalaculturalForm],
    }).compileComponents();

    fixture = TestBed.createComponent(MuseosalaculturalForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
