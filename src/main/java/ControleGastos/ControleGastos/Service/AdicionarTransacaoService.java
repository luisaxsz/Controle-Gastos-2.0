package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Service.Deprecated.ContaService;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoDeTransacaoExistente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdicionarTransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final ContaService contaService;
  private final ValidacaoContaExistente validacaoContaExistente;
  private final ValidacaoDeTransacaoExistente validacaoDeTransacaoExistente;
  private final ModelMapper mapper;

  public Transacao adicionarTransacao(Transacao transacao) {
    if (validacaoContaExistente.validar(transacao.getConta().getId())) {
      //Busca informações da conta
      //Conta conta = contaRepository.getReferenceById(idConta);
      //transacao.setConta(conta);
      transacaoRepository.save(transacao);
      //Atualiza total
      //contaService.saveTotal(idConta, transacao);
      return transacao;
    }
    throw new RuntimeException("Transacao não adicionada");
  }
}
