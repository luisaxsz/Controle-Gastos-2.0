import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(
  ) { }

  isAutenticado: boolean = false;


  setAutenticado(valor: boolean): void {
    this.isAutenticado = valor;
  }
}
