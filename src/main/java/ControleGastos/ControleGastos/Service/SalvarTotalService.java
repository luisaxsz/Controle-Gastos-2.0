package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Domain.Model.TipoTransacao;
import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SalvarTotalService {

  private final ContaRepository contaRepository;
  private final ValidacaoContaExistente validacaoContaExistente;

  public void salvarTotal(Integer id, Transacao transacao) {
    Conta conta = contaRepository.getReferenceById(id);
    BigDecimal novoTotal = BigDecimal.valueOf(0);
    if (transacao.getTipo() == TipoTransacao.GASTO && validacaoContaExistente.validar(id)) {
      novoTotal = conta.getTotal().subtract(transacao.getValor());
    } else if (transacao.getTipo() == TipoTransacao.LUCRO && validacaoContaExistente.validar(id)) {
      novoTotal = conta.getTotal().add(transacao.getValor());
    }
    conta.setTotal(novoTotal);
    contaRepository.save(conta);
  }
}
