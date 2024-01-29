import { Component, Input, OnInit } from '@angular/core';
import { Conta } from '../../../interfaces/conta/conta';
import { AuthServiceService } from '../../../services/conta/auth-service.service';
import { Router } from '@angular/router';
import { ContaService } from '../../../services/conta/conta.service';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css'],
})
export class ContaComponent implements OnInit {
  conta: Conta = {
    nome: '',
    sobrenome: '',
    telefone: '',
    email: '',
    senha: '',
    transacoes: [],
  };

  isAutenticado: boolean = this.authService.isAutenticado;

  constructor(
    private authService: AuthServiceService,
    private router: Router,
    private service: ContaService
  ) {}

  ngOnInit(): void {
    this.service
      .buscarPorEmail(this.service.contaDTO.email)
      .subscribe((conta) => {
        this.conta = conta;
        this.router.navigate(['telaPrincipal/transacoes/', this.conta.id]);
      });
  }

  editarConta(): void {
    this.router.navigate(['telaPrincipal/editarConta/', this.conta.id]);
  }

  fazerLogout(): void {
    this.authService.setAutenticado(false);
    this.router.navigate(['login']);
  }

  adicionarTransacao() {
    this.router.navigate(['telaPrincipal/adicionarTransacao', this.conta.id]);
  }
}
