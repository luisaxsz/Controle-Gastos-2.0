import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http"
import { Conta } from './conta';
import { ContaDTO } from './contaDTO';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(
    private http: HttpClient
  ) { }

  API = 'http://localhost:8080/contas'

  criarConta(conta: Conta){
    return this.http.post<Conta>(this.API,conta);
  }

  fazerLogin(contaDTO: ContaDTO){
    const url = `${this.API}/login`
    return this.http.post<ContaDTO>(url,contaDTO)
  }
}
