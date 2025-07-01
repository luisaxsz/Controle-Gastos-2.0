package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Domain.Model.Usuario;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidacaoEmail {

//    private final ContaRepository contaRepository;
//
//    public boolean validar(Usuario conta) {
//        Optional<Usuario> contaEmail = contaRepository.findByEmail(conta.getEmail());
//        if (contaEmail.isPresent()) {
//            throw new IllegalArgumentException("Email já existente");
//        }
//        return true;
//    }
}
