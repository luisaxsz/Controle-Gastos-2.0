import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http"
import { Conta } from './conta';
import { ContaDTO } from './contaDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(
    private http: HttpClient
  ) { }

  API = 'http://localhost:8080/contas'
  logado: boolean = false

  criarConta(conta: Conta){
    return this.http.post<Conta>(this.API,conta);
  }

  fazerLogin(contaDTO: ContaDTO): Observable<ContaDTO>{
    const url = `${this.API}/login`
    return this.http.post<ContaDTO>(url,contaDTO)
  }
}
