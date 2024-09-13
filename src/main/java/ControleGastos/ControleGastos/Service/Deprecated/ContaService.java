package ControleGastos.ControleGastos.Service.Deprecated;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Domain.Model.TipoTransacao;
import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoEmail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;

@Service
@Deprecated
public class ContaService {
  @Autowired
  private ContaRepository contaRepository;

  @Autowired
  private ValidacaoEmail validacaoEmail;

  @Autowired
  private ValidacaoContaExistente validacaoContaExistente;

  @Autowired
  private ModelMapper mapper;

//    @Autowired
//    private ValidacaoLogin validacaoLogin;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

  /**
   * Abordagem mudada, agora com autenticação do keycloak apenas usuários autenticados podem acessar endpoints de conta e contan n tem senha.
   * Também foi alterado dtos por views no banco de dados, que retornam apenas os dados necessários.
   */

//    public List<ContaDTO> getContasBd() {
//        return contaRepository.findAll().stream().map(ContaDTO::new).toList();
//    }
//
//    public Optional<ContaDTO> getContaById(Integer id) {
//        Optional<Conta> conta = contaRepository.findById(id);
//        if (validacaoContaExistente.validar(id)) {
//            return Optional.ofNullable(ContaDTO.create(conta.get()));
//        }
//        throw new EntityNotFoundException("A conta não foi encontrada");
//    }

  /**
   * Após inclusão do keycloak, não é mais necessário autenticação por login e senha.
   */
//    public boolean getUserByLogin(SolicitacaoDeLoginDTO loginAuth) throws CredentialException {
//        return validacaoLogin.validar(loginAuth);
//    }

  public void saveTotal(Integer id, Transacao transacao) {
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
