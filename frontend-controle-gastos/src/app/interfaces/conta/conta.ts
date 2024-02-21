import { Transacao } from "../transacao/transacao"

export interface Conta{
  id?: number,
  nome: string,
  sobrenome:string,
  telefone: string,
  email: string,
  senha: string,
  total?: number,
  transacoes?: Transacao[]
}
