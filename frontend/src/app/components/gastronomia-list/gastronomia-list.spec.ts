import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GastronomiaeList } from './gastronomia-list';

describe('GastronomiaeList', () => {
  let component: GastronomiaeList;
  let fixture: ComponentFixture<GastronomiaeList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GastronomiaeList],
    }).compileComponents();

    fixture = TestBed.createComponent(GastronomiaeList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
