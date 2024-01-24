import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ContaService } from '../conta.service';
import { Router } from '@angular/router';
import { AuthServiceService } from '../auth-service.service';
import { ContaDTO } from '../contaDTO';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private service: ContaService,
    private router: Router,
    private authService: AuthServiceService
  ) {}

  mensagemErro: string = '';

  @Input() contaDTO: ContaDTO = {
    email: '',
    senha:''
  };

  ngOnInit(): void {}

  login(form: NgForm) {
    if (form.valid) {
      this.service.fazerLogin(form.value).subscribe(
        (res) => {
          if (res) {
            this.service.setContaDTO(this.contaDTO);
            this.authService.setAutenticado(true);
            this.router.navigate(['telaPrincipal']);
          } else {
            this.authService.setAutenticado(false);
          }
        },
        (err) => {
          this.authService.setAutenticado(false);
          this.mensagemErro = 'Credencias Inválidas';
        }
      );
    }
  }
  criarConta() {
    this.router.navigate(['/cadastro']);
  }
}
