import { Component, Input, OnInit } from '@angular/core';
import { Conta } from '../conta';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css']
})
export class ContaComponent implements OnInit {

  @Input() conta: Conta = {
    id: 0,
    nome: '',
    sobrenome: '',
    telefone: '',
    senha: '',
    email: '',
    total: 0
  }

  constructor() { }

  ngOnInit(): void {
  }

}
