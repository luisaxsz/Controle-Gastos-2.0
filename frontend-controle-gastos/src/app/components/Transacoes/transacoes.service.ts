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

  listarTransacoes(idConta: number){
    const url = `${this.API}/allConta/${idConta}`
    return this.http.get<Transacao[]>(url);
  }

  buscarTransacaoPorId(id: number){
    const url = `${this.API}/${id}`
    return this.http.get<Transacao>(url);
  }

  editarTransacao(transacao: Transacao): Observable<Transacao>{
    const url = `${this.API}/${transacao.id}`
    return this.http.put<Transacao>(url,transacao);
  }

  adicionarTransacao(transacao: Transacao, idConta: number): Observable<Transacao>{
    const url = `${this.API}/${idConta}`
    return this.http.post<Transacao>(url, transacao)
  }
}
