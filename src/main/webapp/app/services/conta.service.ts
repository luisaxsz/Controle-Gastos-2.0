import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http"
import { Conta } from '../interfaces/conta/conta';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContaService {

  constructor(
    private http: HttpClient
  ) { }

  private readonly API = 'http://localhost:8080/contas'

  criarConta(conta: Conta){
    return this.http.post<Conta>(this.API,conta);
  }

  buscarPorId(id: number): Observable<Conta>{
    return this.http.get<Conta>(`${this.API}/${id}`)
  }

  editar(conta: Conta, id: number): Observable<Conta>{
    return this.http.put<Conta>(`${this.API}/${id}`,conta);
  }

  // setContaDTO(contaDTO: ContaDTO){
  //   this.contaDTO = contaDTO;
  // }

  // buscarPorEmail(email: string): Observable<Conta>{
  //   const url = `${this.API}/email/${email}`
  //   return this.http.get<Conta>(url)
  // }

  /**
   * Depreciado após inclusão do Keycloak
   */
  // fazerLogin(contaDTO: ContaDTO): Observable<ContaDTO>{
  //   const url = `${this.API}/login`
  //   return this.http.post<ContaDTO>(url,contaDTO)
  // }
}

