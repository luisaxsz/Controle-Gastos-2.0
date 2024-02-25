import { Injectable } from '@angular/core';

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
