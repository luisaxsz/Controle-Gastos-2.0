import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrasacoesComponent } from './trasacoes.component';

describe('TrasacoesComponent', () => {
  let component: TrasacoesComponent;
  let fixture: ComponentFixture<TrasacoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TrasacoesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TrasacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
