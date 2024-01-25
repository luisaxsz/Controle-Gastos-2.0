import { Component, Input, OnInit } from '@angular/core';
import { Transacao } from '../transacao';
import {Big} from 'big.js';

@Component({
  selector: 'app-trasacoes',
  templateUrl: './trasacoes.component.html',
  styleUrls: ['./trasacoes.component.css']
})
export class TrasacoesComponent implements OnInit {

  @Input() transacao: Transacao = {
    id: 0,
    tipo: "",
    valor: Big(0).toNumber(),
    descricao:""
  }

  @Input() listaTransacoes: Transacao[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
