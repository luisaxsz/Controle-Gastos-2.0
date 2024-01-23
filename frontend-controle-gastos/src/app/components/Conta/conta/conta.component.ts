import { Component, Input, OnInit } from '@angular/core';
import { Conta } from '../conta';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-conta',
  templateUrl: './conta.component.html',
  styleUrls: ['./conta.component.css'],
})
export class ContaComponent implements OnInit {
  @Input() conta: Conta = {
    id: 0,
    nome: '',
    sobrenome: '',
    telefone: '',
    senha: '',
    email: '',
    total: 0,
  };

  constructor(
    private authService: AuthServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.authService.setAutenticado(false);
  }

  fazerLogout() {
    this.authService.setAutenticado(false);
    this.router.navigate(['login']);
  }
}
