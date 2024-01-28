import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../Conta/auth-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthServiceService
  ) { }


  ngOnInit(): void {
  }

  isAutenticado: boolean = this.authService.isAutenticado

}
