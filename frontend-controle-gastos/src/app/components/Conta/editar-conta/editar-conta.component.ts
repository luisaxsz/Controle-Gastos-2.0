import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContaService } from '../../../services/conta/conta.service';
import { NgForm } from '@angular/forms';
import { Conta } from '../../../interfaces/conta/conta';

@Component({
  selector: 'app-editar-conta',
  templateUrl: './editar-conta.component.html',
  styleUrls: ['./editar-conta.component.css'],
})
export class EditarContaComponent implements OnInit {
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
  constructor(
    private router: Router,
    private contaService: ContaService,
    private route: ActivatedRoute
  ) {}

  id = parseInt(this.route.snapshot.paramMap.get('id')!);

  ngOnInit() {
    this.contaService.buscarPorId(this.id).subscribe((conta) => {
      this.conta = conta;
      this.preencherForm(conta, this.meuFormulario);
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
        .editar(this.meuFormulario.value, this.id)
        .subscribe(() =>
          this.router.navigate(['telaPrincipal/transacoes', this.conta.id])
        );
    }
  }

  cancelar() {
    this.router.navigate(['telaPrincipal/transacoes', this.conta.id]);
  }
}
