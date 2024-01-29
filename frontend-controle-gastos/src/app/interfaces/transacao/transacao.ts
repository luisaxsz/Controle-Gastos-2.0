import { Conta } from "../conta/conta";

export interface Transacao{
  id?: number,
  tipo: string,
  valor: number,
  descricao: string,
  conta: Conta
}
