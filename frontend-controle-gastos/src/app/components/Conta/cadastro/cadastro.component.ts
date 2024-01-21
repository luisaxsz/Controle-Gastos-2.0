import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ContaService } from '../conta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  constructor(
    private service: ContaService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  cadastrarConta(form:NgForm){
    if(form.valid){
      this.service.criarConta(form.value).subscribe(() => this.router.navigate(['/login']))
    }else{
      alert("Formulário inválido: Preencha corretamente todos os campos")
    }
  }

  cancelar(){
    this.router.navigate(['/login'])
  }
}
