import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ContaService } from '../conta.service';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private service: ContaService,
    private router: Router,
  ) { }

  mensagemErro: string = ''

  ngOnInit(): void {
  }

  login(form: NgForm){
    if(form.valid){
      this.service.fazerLogin(form.value).subscribe(res =>{
        if(res){
          this.router.navigate(['/telaPrincipal'])
        }
      }, err => {
        this.mensagemErro = 'Credencias Inválidas'
      })
    }
  }

  criarConta(){
    this.router.navigate(['cadastro'])
  }
}
