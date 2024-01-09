package ControleGastos.ControleGastos.Conta;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public Iterable<Conta> getContasBd(){
        return contaRepository.findAll();
    }

    public Optional<Conta> getContaById(Integer id){
        return contaRepository.findById(id);
    }

}
