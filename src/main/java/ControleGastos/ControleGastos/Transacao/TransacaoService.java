package ControleGastos.ControleGastos.Transacao;

import ControleGastos.ControleGastos.Conta.ContaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Transacao> getTransacoesBd(){
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> getTransacaoById(Integer id){
        return Optional.ofNullable(transacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada")));
    }

    public List<Transacao> getTransaçõesByTipo(Transacao.TipoTransacao tipo){
        return transacaoRepository.getTransacoesByTipo(tipo);
    }

    public Transacao adicionarTransacao(Integer id,Transacao transacao){
        Assert.isNull(transacao.getId(), "Transação não processada");
        Transacao transacaoAdicionada = transacaoRepository.save(transacao);
        contaService.saveTotal(id,transacao);
        return transacaoAdicionada;
    }

    public Transacao atualizarTransacao(Transacao transacao, Integer id, Integer idConta) {
        Optional<Transacao> optionalTransacao = getTransacaoById(id);
        if (optionalTransacao.isPresent()) {
            Transacao db = optionalTransacao.get();
            db.setDescricao(transacao.getDescricao());
            db.setValor(transacao.getValor());
            db.setTipo(transacao.getTipo());
            transacaoRepository.save(db);
            contaService.saveTotal(idConta,transacao);
            return db;
        }else {
            throw  new EntityNotFoundException("Transação não encontrada");
        }
    }
    public void deleteTransacao(Integer id){
        if(getTransacaoById(id).isPresent()){
            transacaoRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Transação não encontrada");
        }
    }
}
