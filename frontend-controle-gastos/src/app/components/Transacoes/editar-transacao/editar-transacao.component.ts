import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TransacoesService } from '../transacoes.service';
import { Transacao } from '../transacao';

@Component({
  selector: 'app-editar-transacao',
  templateUrl: './editar-transacao.component.html',
  styleUrls: ['./editar-transacao.component.css'],
})
export class EditarTransacaoComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private trasacaoService: TransacoesService,
    private router: Router
  ) {}

  transacao: Transacao = {
    tipo: '',
    valor: 0,
    descricao: '',
    conta: {
      nome: '',
      sobrenome: '',
      email: '',
      telefone: '',
      senha: '',
      total: 0,
    },
  };

  id = this.route.snapshot.paramMap.get('id');

  ngOnInit(): void {
    this.trasacaoService
      .buscarTransacaoPorId(parseInt(this.id!))
      .subscribe((transacao) => (this.transacao = transacao));
  }

  editarTransacao(form: NgForm) {
    if(form.valid){
      this.trasacaoService.editarTransacao(form.value).subscribe(() => this.router.navigate(['telaPrincipal/transacoes', this.transacao.conta.id])  )
    }
  }

  cancelar() {
    this.router.navigate(['telaPrincipal/transacoes', this.transacao.conta.id])
  }
}
