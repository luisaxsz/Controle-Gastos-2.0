package ControleGastos.ControleGastos.Transacao;

import ControleGastos.ControleGastos.Conta.Conta;
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

    public List<Transacao> getTransacoesBd(){
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> getTransacaoById(Integer id){
        return Optional.ofNullable(transacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transação não encontrada")));
    }

    public List<Transacao> getTransaçõesByTipo(Transacao.TipoTransacao tipo){
        return transacaoRepository.getTransacoesByTipo(tipo);
    }

    public Transacao adicionarTransacao(Transacao transacao){
        Assert.isNull(transacao.getId(), "Transação não processada");
        return transacaoRepository.save(transacao);
    }

    public Transacao atualizarTransacao(Transacao transacao, Integer id) {
        Optional<Transacao> optionalTransacao = getTransacaoById(id);
        if (optionalTransacao.isPresent()) {
            Transacao db = optionalTransacao.get();
            db.setDescricao(transacao.getDescricao());
            db.setValor(transacao.getValor());
            db.setTipo(transacao.getTipo());
            transacaoRepository.save(db);
            return db;
        }else {
            throw  new EntityNotFoundException("Transação não encontrada");
        }
    }
    public void delete (Integer id){
        if(getTransacaoById(id).isPresent()){
            transacaoRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Transação não encontrada");
        }
    }
}
