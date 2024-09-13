package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Domain.Model.TipoTransacao;
import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class SalvarTotalService {

  private final ContaRepository contaRepository;

  public void salvarTotal(Transacao transacao) {
    Conta conta = contaRepository.findById(transacao.getConta().getId()).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
    BigDecimal novoTotal = BigDecimal.valueOf(0);
    if (transacao.getTipo() == TipoTransacao.GASTO) {
      novoTotal = conta.getTotal().subtract(transacao.getValor());
    } else if (transacao.getTipo() == TipoTransacao.LUCRO) {
      novoTotal = conta.getTotal().add(transacao.getValor());
    }
    conta.setTotal(novoTotal);
    contaRepository.save(conta);
  }
}
