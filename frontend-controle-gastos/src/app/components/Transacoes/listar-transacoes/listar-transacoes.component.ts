import { Component, OnInit } from '@angular/core';
import { Transacao } from '../transacao';
import { TransacoesService } from '../transacoes.service';

@Component({
  selector: 'app-listar-transacoes',
  templateUrl: './listar-transacoes.component.html',
  styleUrls: ['./listar-transacoes.component.css']
})
export class ListarTransacoesComponent implements OnInit {

  listarTransacoes: Transacao[] = []

  constructor(
    private service: TransacoesService,
  ) { }

  ngOnInit(): void {
    this.service.listarTransacoes().subscribe((listarTransacoes) => this.listarTransacoes = listarTransacoes)
  }

}
