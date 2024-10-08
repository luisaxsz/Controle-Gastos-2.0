import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http"
import { Conta } from '../../interfaces/conta/conta';
import { Observable } from 'rxjs';
import {ContaDTO} from "../../interfaces/conta/contaDTO";

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = 'http://localhost:8080/contas'

  contaDTO!: ContaDTO;


  criarConta(conta: Conta){
    return this.http.post<Conta>(this.API,conta);
  }

  fazerLogin(contaDTO: ContaDTO): Observable<ContaDTO>{
    const url = `${this.API}/login`
    return this.http.post<ContaDTO>(url,contaDTO)
  }

  buscarPorId(id: number): Observable<Conta>{
    const url = `${this.API}/${id}`
    return this.http.get<Conta>(url)
  }

  buscarPorEmail(email: string): Observable<Conta>{
    const url = `${this.API}/email/${email}`
    return this.http.get<Conta>(url)
  }

  editar(conta: Conta, id: number): Observable<Conta>{
    const url = `${this.API}/${id}`
    return this.http.put<Conta>(url,conta);
  }

  setContaDTO(contaDTO: ContaDTO){
    this.contaDTO = contaDTO;
  }
}
