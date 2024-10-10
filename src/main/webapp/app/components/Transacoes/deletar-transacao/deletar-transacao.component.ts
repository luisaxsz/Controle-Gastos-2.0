import { Transacao } from './../../../interfaces/transacao/transacao';
import { Component, OnInit } from '@angular/core';
import { TransacoesService } from '../../../services/transacoes.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-deletar-transacao',
  templateUrl: './deletar-transacao.component.html',
  styleUrls: ['./deletar-transacao.component.css']
})
export class DeletarTransacaoComponent implements OnInit {

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private transacaoService : TransacoesService
  ) { }

  transacao: Transacao = {
    tipo: '',
    valor: 0,
    descricao: '',
    conta: 0
  };


    ngOnInit(): void {
      const id = parseInt(this.route.snapshot.paramMap.get('id')!);
      this.transacaoService.buscarTransacaoPorId(id).subscribe(
        transacao => this.transacao = transacao
      )
    }

    deletarTransacao(): void{
      if(this.transacao.id){
        this.transacaoService.deletarTransacao(this.transacao.id).subscribe(() => this.router.navigate(['telaPrincipal/transacoes', this.transacao.conta]))
      }
    }

    cancelar(): void{
      this.router.navigate(['telaPrincipal/transacoes', this.transacao.conta])
    }
}
