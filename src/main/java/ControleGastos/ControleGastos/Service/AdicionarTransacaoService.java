package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Service.Deprecated.ContaService;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoDeTransacaoExistente;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AdicionarTransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final ContaService contaService;
  private final ValidacaoContaExistente validacaoContaExistente;
  private final ValidacaoDeTransacaoExistente validacaoDeTransacaoExistente;
  private final ModelMapper mapper;

  public Transacao adicionarTransacao(Transacao transacao) {
    if (validacaoContaExistente.validar(transacao.getConta().getId())) {
      transacaoRepository.save(transacao);
      log.debug("Conta: {}", transacao.getConta().getId());
      //Atualiza total
      contaService.saveTotal(transacao.getConta().getId(), transacao);
      return transacao;
    }
    throw new RuntimeException("Transacao não adicionada");
  }
}
