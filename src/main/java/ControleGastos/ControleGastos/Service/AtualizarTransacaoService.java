package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AtualizarTransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final SalvarTotalService salvarTotalService;
  private final ModelMapper mapper;

  public Transacao atualizarTransacao(Transacao resource, Integer id){
    Transacao transacao = transacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada"));
    resource.setId(id);
    mapper.map(resource, transacao);
    salvarTotalService.salvarTotal(transacao);
    transacaoRepository.save(transacao);
    return transacao;
  }
}
