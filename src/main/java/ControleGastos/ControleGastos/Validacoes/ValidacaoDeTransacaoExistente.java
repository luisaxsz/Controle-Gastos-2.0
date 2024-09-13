package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@AllArgsConstructor
public class ValidacaoDeTransacaoExistente {


    private final TransacaoRepository transacaoRepository;

    public boolean validar(Integer id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        if (transacao.isPresent()) {
            return true;
        }
        throw new EntityNotFoundException("Conta não existe");
    }
}
