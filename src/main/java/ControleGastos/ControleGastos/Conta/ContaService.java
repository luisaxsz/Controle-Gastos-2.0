package ControleGastos.ControleGastos.Conta;

import ControleGastos.ControleGastos.Transacao.Transacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> getContasBd() {
        return contaRepository.findAll();
    }

    public Optional<Conta> getContaById(Integer id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()){
            return contaRepository.findById(id);
        }else {
            throw new EntityNotFoundException("A conta não foi encontrada");
        }
    }

    public Conta criarConta(Conta conta){
        Assert.isNull(conta.getId(), "Não foi possível inserir carro");
        return contaRepository.save(conta);
    }

    public Conta update(Conta conta, Integer id) {
        Optional<Conta> optionalConta = getContaById(id);
        if (optionalConta.isPresent()) {
            Conta db = optionalConta.get();
            db.setNome(conta.getNome());
            db.setSobrenome(conta.getSobrenome());
            contaRepository.save(db);
            return db;
        }else {
            throw  new EntityNotFoundException("Conta não encontrada");
        }

    }

    public Conta saveTotal(Integer id, Transacao transacao) {
        Optional<Conta> optionalConta = getContaById(id);

        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            BigDecimal novoTotal;

            if (transacao.getTipo() == Transacao.TipoTransacao.GASTO) {
                novoTotal = conta.getTotal().subtract(transacao.getValor());
            } else {
                novoTotal = conta.getTotal().add(transacao.getValor());
            }

            conta.setTotal(novoTotal);
            return contaRepository.save(conta);
        } else {
            throw new EntityNotFoundException("Conta não encontrada com ID: " + id);
        }
    }

    public void delete (Integer id){
        if(getContaById(id).isPresent()){
            contaRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Conta de id: " + id +"não encontrada");
        }
    }

}
