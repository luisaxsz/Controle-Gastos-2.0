package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.DTO.SolicitacaoDeLoginDTO;
import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

@Component
public class ValidacaoLogin {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean validar(SolicitacaoDeLoginDTO loginAuth) throws CredentialException {
        Optional<Conta> contaOptional = contaRepository.findByEmail(loginAuth.getEmail());
        if (contaOptional.isPresent() && passwordEncoder.matches(loginAuth.getSenha(), contaOptional.get().getSenha())) {
            return true;
        } else if (contaOptional.isEmpty()) {
            throw new CredentialException("Email não existente");
        }
        throw new CredentialException("Credenciais Inválidas");
    }
}
