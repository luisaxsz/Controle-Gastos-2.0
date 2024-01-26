import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { TransacoesService } from '../transacoes.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ContaService } from '../../Conta/conta.service';
import { Conta } from '../../Conta/conta';

@Component({
  selector: 'app-adicionar-transacao',
  templateUrl: './adicionar-transacao.component.html',
  styleUrls: ['./adicionar-transacao.component.css']
})
export class AdicionarTransacaoComponent implements OnInit {

  constructor(
    private serviceTransacao: TransacoesService,
    private router: Router,
    private route: ActivatedRoute
    ) { }

  id = this.route.snapshot.paramMap.get('id');

  ngOnInit(): void {
  }

  adicionarTransacao(form: NgForm){
    if(form.valid){
      this.serviceTransacao.adicionarTransacao(form.value, parseInt(this.id!)).subscribe(() => this.router.navigate(['telaPrincipal/transacoes/', parseInt(this.id!)]))
    }
  }

  cancelar(){
    this.router.navigate(['telaPrincipal/transacoes/', parseInt(this.id!)])
  }
}
