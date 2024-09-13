package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdicionarTransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final SalvarTotalService salvarTotalService;
  private final ValidacaoContaExistente validacaoContaExistente;

  public Transacao adicionarTransacao(Transacao transacao) {
    if (validacaoContaExistente.validar(transacao.getConta().getId())) {
      transacaoRepository.save(transacao);
      //Atualiza total
      salvarTotalService.salvarTotal(transacao);
      return transacao;
    }
    throw new RuntimeException("Transacao não adicionada");
  }
}
