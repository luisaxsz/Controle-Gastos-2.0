import { Component, OnInit } from '@angular/core';
import { Transacao } from '../../../interfaces/transacao/transacao';
import { TransacoesService } from '../../../services/transacoes.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-listar-transacoes',
  templateUrl: './listar-transacoes.component.html',
  styleUrls: ['./listar-transacoes.component.css']
})
export class ListarTransacoesComponent implements OnInit {

  listaTransacoes: Transacao[] = []
  id = this.route.snapshot.paramMap.get('id');

  constructor(
    private service: TransacoesService,
    private route: ActivatedRoute
  ) { }


  ngOnInit(): void {
    this.service.listarTransacoes(parseInt(this.id!)).subscribe(listarTransacoes => this.listaTransacoes = listarTransacoes)
  }

}
