import { Component, Input, OnInit } from '@angular/core';
import { Transacao } from '../transacao';
import { Big } from 'big.js';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trasacoes',
  templateUrl: './trasacoes.component.html',
  styleUrls: ['./trasacoes.component.css'],
})
export class TrasacoesComponent implements OnInit {
  @Input() transacao: Transacao = {
    tipo: '',
    valor: Big(0).toNumber(),
    descricao: '',
    conta: {
      nome: '',
      sobrenome: '',
      telefone: '',
      email: '',
      senha: '',
      total: Big(0).toNumber(),
    },
  };

  @Input() listaTransacoes: Transacao[] = [];

  constructor() {}

  ngOnInit(): void {}
}
