import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TransacoesService } from '../../../services/transacoes.service';
import { Transacao } from '../../../interfaces/transacao/transacao';

@Component({
  selector: 'app-editar-transacao',
  templateUrl: './editar-transacao.component.html',
  styleUrls: ['./editar-transacao.component.css'],
})
export class EditarTransacaoComponent implements OnInit {
  @ViewChild('formEditarTransacao') meuFormulario!: NgForm;

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

  id = parseInt(this.route.snapshot.paramMap.get('id')!);

  constructor(
    private route: ActivatedRoute,
    private trasacaoService: TransacoesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.trasacaoService
      .buscarTransacaoPorId(this.id)
      .subscribe((transacao) => {
        this.transacao = transacao;
        this.preencherForm(transacao, this.meuFormulario);
      });
  }

  editarTransacao(form: NgForm) {
    if (form.valid) {
      this.trasacaoService
        .editarTransacao(
          this.meuFormulario.value,
          this.id,
          this.transacao.conta.id!
        )
        .subscribe(() =>
          this.router.navigate([
            'telaPrincipal/transacoes',
            this.transacao.conta.id,
          ])
        );
    }
  }

  preencherForm(dados: any, form: NgForm) {
    form.form.patchValue({
      tipo: dados.tipo,
      valor: dados.valor,
      descricao: dados.descricao,
    });
  }

  cancelar() {
    this.router.navigate(['telaPrincipal/transacoes', this.transacao.conta.id]);
  }
}
