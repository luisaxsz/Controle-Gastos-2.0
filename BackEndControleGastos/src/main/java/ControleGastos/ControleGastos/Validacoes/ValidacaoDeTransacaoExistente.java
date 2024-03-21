package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class ValidacaoDeTransacaoExistente {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public boolean validar(Integer id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        if (transacao.isPresent()) {
            return true;
        }
        throw new EntityNotFoundException("Conta não existe");
    }
}
