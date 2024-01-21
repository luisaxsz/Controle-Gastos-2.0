import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensagensErroComponent } from './mensagens-erro.component';

describe('MensagensErroComponent', () => {
  let component: MensagensErroComponent;
  let fixture: ComponentFixture<MensagensErroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MensagensErroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MensagensErroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
