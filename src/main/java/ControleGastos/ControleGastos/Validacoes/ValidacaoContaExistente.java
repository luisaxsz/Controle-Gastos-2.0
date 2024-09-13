package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ValidacaoContaExistente {

    @Autowired
    private ContaRepository contaRepository;

    public boolean validar(Integer id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            return true;
        }

        throw new EntityNotFoundException("Conta não existe");

    }
}
