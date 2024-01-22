import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../Conta/auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tela-principal',
  templateUrl: './tela-principal.component.html',
  styleUrls: ['./tela-principal.component.css']
})
export class TelaPrincipalComponent implements OnInit {

  constructor(
    private authService: AuthServiceService,
    private route: Router
    ) { }

  ngOnInit(): void {
    this.authService.setAutenticado(false)
  }

  fazerLogout(){
    this.authService.setAutenticado(false);
    this.route.navigate(['login'])
  }
}
