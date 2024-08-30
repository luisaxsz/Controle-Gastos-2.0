import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletarTransacaoComponent } from './deletar-transacao.component';

describe('DeletarTransacaoComponent', () => {
  let component: DeletarTransacaoComponent;
  let fixture: ComponentFixture<DeletarTransacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletarTransacaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletarTransacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
