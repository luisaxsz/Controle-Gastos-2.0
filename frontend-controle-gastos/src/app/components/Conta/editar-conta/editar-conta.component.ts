import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContaService } from '../conta.service';
import { NgForm } from '@angular/forms';
import { Conta } from '../conta';

@Component({
  selector: 'app-editar-conta',
  templateUrl: './editar-conta.component.html',
  styleUrls: ['./editar-conta.component.css'],
})
export class EditarContaComponent implements OnInit {
  constructor(
    private router: Router,
    private contaService: ContaService,
    private route: ActivatedRoute
  ) {}

  @ViewChild('formEditar') meuFormulario!: NgForm;

  conta: Conta = {
    id: 0,
    nome: '',
    sobrenome: '',
    telefone: '',
    email: '',
    senha: '',
    total: 0,
  };

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.contaService.buscarPorId(parseInt(id!)).subscribe((conta) => {
      this.conta = conta;
      this.preencherForm(conta, this.meuFormulario)
    });
  }

  preencherForm(dados: any, form: NgForm) {
    form.form.patchValue({
      nome: dados.nome,
      sobrenome: dados.sobrenome,
      telefone: dados.telefone,
      email: dados.email,
    });
  }

  editarConta(form: NgForm) {
    if (form.valid) {
      return this.contaService
        .editar(this.conta)
        .subscribe(() => this.router.navigate(['telaPrincipal/transacoes', this.conta.id]));
    }
  }

  cancelar() {
    this.router.navigate(['telaPrincipal/transacoes', this.conta.id]);
  }
}
