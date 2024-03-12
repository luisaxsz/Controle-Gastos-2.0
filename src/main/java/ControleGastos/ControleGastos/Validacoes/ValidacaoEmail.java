package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ValidacaoEmail {

    @Autowired
    private ContaRepository contaRepository;

    public boolean validar(Conta conta) {
        Optional<Conta> contaEmail = contaRepository.findByEmail(conta.getEmail());
        if (contaEmail.isPresent()) {
            return true;
        }
        throw new IllegalArgumentException("Email já existente");
    }
}
