package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Model.TipoTransacao;
import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoDeTransacaoExistente;
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
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;

    @Autowired
    private ValidacaoContaExistente validacaoContaExistente;

    @Autowired
    private ValidacaoDeTransacaoExistente validacaoDeTransacaoExistente;

    @Query("SELECT * FROM TRANSACAO WHERE transacao.conta_id = conta.id;")
    public List<Transacao> getTransacoesBd(Integer idConta) {
        return transacaoRepository.findAll();
    }

    public Transacao getTransacaoById(Integer id) {
        if (validacaoDeTransacaoExistente.validar(id)) {
            Transacao transacao = transacaoRepository.getReferenceById(id);
            return transacao;
        }
        throw new EntityNotFoundException("Transação não encontrada");
    }


    public List<Transacao> getTransaçõesByTipo(TipoTransacao tipo) {
        return transacaoRepository.getTransacoesByTipo(tipo);
    }

    public void adicionarTransacao(Integer idConta, Transacao transacao) {
        if (validacaoContaExistente.validar(idConta)) {
            //Busca informações da conta
            Conta conta = contaRepository.getReferenceById(idConta);
            transacao.setConta(conta);
            transacaoRepository.save(transacao);
            //Atualiza total
            contaService.saveTotal(idConta, transacao);
        }
    }

    public Transacao atualizarTransacao(Transacao transacao, Integer id, Integer idConta) {
        if (validacaoDeTransacaoExistente.validar(id)) {
            Transacao db = getTransacaoById(id);
            db.setDescricao(transacao.getDescricao());
            db.setValor(transacao.getValor());
            db.setTipo(transacao.getTipo());
            transacaoRepository.save(db);
            contaService.saveTotal(idConta, transacao);
            return db;
        }
        throw new RuntimeException("Transação não aprovada");
    }

    //obs refatorar -> Atualizar total ao retirar transacao
    public void deleteTransacao(Integer id) {
        if (validacaoDeTransacaoExistente.validar(id)) {
            transacaoRepository.deleteById(id);
        }
    }
}

