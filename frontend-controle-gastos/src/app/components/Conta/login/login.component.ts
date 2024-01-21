import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ContaService } from '../conta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private service: ContaService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  login(form: NgForm){

  }

  criarConta(){
    this.router.navigate(['cadastro'])
  }
}
