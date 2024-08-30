import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transacao } from '../interfaces/transacao/transacao';
import { Observable } from 'rxjs';

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

  buscarTransacaoPorId(id: number):Observable<Transacao>{
    const url = `${this.API}/${id}`
    return this.http.get<Transacao>(url);
  }

  editarTransacao(transacao: Transacao, idTransacao: number ,idConta: number): Observable<Transacao>{
    const url = `${this.API}/${idTransacao}/conta/${idConta}`
    return this.http.put<Transacao>(url,transacao);
  }

  adicionarTransacao(transacao: Transacao, idConta: number): Observable<Transacao>{
    const url = `${this.API}/${idConta}`
    return this.http.post<Transacao>(url, transacao)
  }

  deletarTransacao(id: number): Observable<Transacao>{
    const url = `${this.API}/${id}`
    return this.http.delete<Transacao>(url);
  }
}
