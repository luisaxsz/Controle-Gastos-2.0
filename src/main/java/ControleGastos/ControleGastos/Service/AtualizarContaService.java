package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoEmail;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AtualizarContaService {

  private final ContaRepository contaRepository;
  private final ValidacaoEmail validacaoEmail;
  private final ModelMapper mapper;

  public Conta atualizarConta(Conta resource, Integer id) {
    Conta conta = contaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    if (validacaoEmail.validar(resource)) {
      resource.setId(id);
      mapper.map(resource, conta);
    }
    return contaRepository.save(conta);
  }
}
