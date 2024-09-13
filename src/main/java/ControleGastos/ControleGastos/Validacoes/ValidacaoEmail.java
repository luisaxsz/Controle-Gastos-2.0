package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidacaoEmail {

    private final ContaRepository contaRepository;

    public boolean validar(Conta conta) {
        Optional<Conta> contaEmail = contaRepository.findByEmail(conta.getEmail());
        if (contaEmail.isPresent()) {
            throw new IllegalArgumentException("Email já existente");
        }
        return true;
    }
}
