import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContaService } from '../conta.service';
import { NgForm } from '@angular/forms';

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
  id = this.route.snapshot.paramMap.get('id');

  ngOnInit() {
    this.contaService.buscarPorId(parseInt(this.id!)).subscribe((resposta) => {
      this.preencherForm(resposta);
    });
  }

  preencherForm(dados: any) {
    this.meuFormulario.form.setValue({
      id: dados.id,
      nome: dados.nome,
      sobrenome: dados.sobrenome,
      telefone: dados.telefone,
      email: dados.email,
      senha: dados.senha,
    });
  }

  editarConta() {
    console.log(this.meuFormulario.value);
    if (this.meuFormulario.valid) {
      return this.contaService
        .editar(this.meuFormulario.value, parseInt(this.id!))
        .subscribe(() => this.router.navigate(['telaPrincipal']));
    }
  }

  cancelar() {
    this.router.navigate(['telaPrincipal']);
  }
}
