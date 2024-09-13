package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoEmail;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CriarContaService {

  private final ValidacaoEmail validacaoEmail;
  private final ContaRepository contaRepository;

  public Conta criarConta(Conta conta) {
    if (validacaoEmail.validar(conta)) {
      return contaRepository.save(conta);
    }
    throw new IllegalArgumentException("Email inválido");
  }

}
