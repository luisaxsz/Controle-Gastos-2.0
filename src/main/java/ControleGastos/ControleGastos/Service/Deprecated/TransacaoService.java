package ControleGastos.ControleGastos.Service.Deprecated;

import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoDeTransacaoExistente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Deprecated
public class TransacaoService {

  private final TransacaoRepository transacaoRepository;
//  private final ContaRepository contaRepository;
  private final ContaService contaService;
  private final ValidacaoContaExistente validacaoContaExistente;
  private final ValidacaoDeTransacaoExistente validacaoDeTransacaoExistente;
  private final ModelMapper mapper;



//  public Optional<TransacaoDTO> getTransacaoById(Integer id) {
//    Optional<Transacao> transacao = transacaoRepository.findById(id);
//    if (validacaoDeTransacaoExistente.validar(id)) {
//      return Optional.ofNullable(TransacaoDTO.create(transacao.get()));
//    }
//    throw new EntityNotFoundException("A conta não foi encontrada");
//  }

//
//  public List<TransacaoDTO> getTransaçõesByTipo(TipoTransacao tipo) {
//    return transacaoRepository.getTransacoesByTipo(tipo).stream().map(TransacaoDTO::new).toList();
//  }

//  public Transacao adicionarTransacao(Integer idConta, Transacao transacao) {
//    if (validacaoContaExistente.validar(idConta)) {
//      //Busca informações da conta
//      //Conta conta = contaRepository.getReferenceById(idConta);
//      //transacao.setConta(conta);
//      transacaoRepository.save(transacao);
//      //Atualiza total
//      contaService.saveTotal(idConta, transacao);
//      return transacao;
//    }
//    throw new RuntimeException("Transacao não adicionada");
//  }

//  public Transacao atualizarTransacao(Transacao resource, Integer id, Integer idConta) {
//      if (validacaoDeTransacaoExistente.validar(id)) {
//        Transacao db = transacaoRepository.getReferenceById(id);
//        db.setDescricao(transacao.getDescricao());
//        db.setValor(transacao.getValor());
//        db.setTipo(transacao.getTipo());
//        transacaoRepository.save(db);
//        contaService.saveTotal(idConta, transacao);
//        return db;
//      }
//      throw new RuntimeException("Transação não aprovada");
//  }

//  obs refatorar -> Atualizar total ao retirar transacao
//  public void deleteTransacao(Integer id) {
//    if (validacaoDeTransacaoExistente.validar(id)) {
//      transacaoRepository.deleteById(id);
//    }
//  }

  /**
   * Relacionamento bidimensional removido
   */
//    @Query("SELECT * FROM TRANSACAO WHERE transacao.conta_id = conta.id;")
//    public List<TransacaoDTO> getTransacoesBd(Integer idConta) {
//        return transacaoRepository.findAll().stream().map(TransacaoDTO::new).toList();
//    }
}

