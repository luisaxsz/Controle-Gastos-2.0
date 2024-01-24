import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transacao } from './transacao';

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
}
