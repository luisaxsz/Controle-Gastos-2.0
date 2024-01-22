import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarTransacoesComponent } from './listar-transacoes.component';

describe('ListarTransacoesComponent', () => {
  let component: ListarTransacoesComponent;
  let fixture: ComponentFixture<ListarTransacoesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListarTransacoesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarTransacoesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
