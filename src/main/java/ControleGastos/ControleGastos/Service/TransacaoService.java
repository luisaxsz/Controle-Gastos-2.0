package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import ControleGastos.ControleGastos.Model.TipoTransacao;
import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaService contaService;

    @Query("SELECT * FROM TRANSACAO WHERE transacao.conta_id = conta.id;")
    public List<Transacao> getTransacoesBd(Integer idConta) {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> getTransacaoById(Integer id) {
        return Optional.ofNullable(transacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada")));
    }

    public List<Transacao> getTransaçõesByTipo(TipoTransacao tipo) {
        return transacaoRepository.getTransacoesByTipo(tipo);
    }

    public Transacao adicionarTransacao(Integer idConta, Transacao transacao) {
        Assert.isNull(transacao.getId(), "Transação não processada");
        Optional<ContaDTO> contaOptional = contaService.getContaById(idConta);
        if (contaOptional.isPresent()) {
            //Conta conta = contaOptional.get();
            //transacao.setConta(conta);
            //Salva transação
            Transacao transacaoAdicionada = transacaoRepository.save(transacao);
            //Atualiza total
            contaService.saveTotal(idConta, transacao);
            return transacaoAdicionada;
        }
        throw new RuntimeException("Transação não processada");
    }

    public Transacao atualizarTransacao(Transacao transacao, Integer id, Integer idConta) {
        Optional<Transacao> optionalTransacao = getTransacaoById(id);
        if (optionalTransacao.isPresent()) {
            Transacao db = optionalTransacao.get();
            db.setDescricao(transacao.getDescricao());
            db.setValor(transacao.getValor());
            db.setTipo(transacao.getTipo());
            transacaoRepository.save(db);
            contaService.saveTotal(idConta, transacao);
            return db;
        } else {
            throw new EntityNotFoundException("Transação não encontrada");
        }
    }

    public void deleteTransacao(Integer id) {
        if (getTransacaoById(id).isPresent()) {
            transacaoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Transação não encontrada");
        }
    }
}
