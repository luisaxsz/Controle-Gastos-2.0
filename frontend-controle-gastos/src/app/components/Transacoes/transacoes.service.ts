import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transacao } from './transacao';
import { Observable } from 'rxjs';
import { Conta } from '../Conta/conta';

@Injectable({
  providedIn: 'root'
})
export class TransacoesService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = "http://localhost:8080/transacoes"

  listarTransacoes(){
    return this.http.get<Transacao[]>(this.API);
  }

  adicionarTransacao(transacao: Transacao, idConta: number): Observable<Transacao>{
    const url = `${this.API}/${idConta}`
    return this.http.post<Transacao>(url, transacao)
  }
}
