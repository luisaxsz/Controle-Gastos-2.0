package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import ControleGastos.ControleGastos.DTO.SolicitacaoDeLoginDTO;
import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Model.TipoTransacao;
import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoEmail;
import ControleGastos.ControleGastos.Validacoes.ValidacaoLogin;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.security.auth.login.CredentialException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ValidacaoLogin validacaoLogin;

    @Autowired
    private ValidacaoEmail validacaoEmail;

    @Autowired
    private ValidacaoContaExistente validacaoContaExistente;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<ContaDTO> getContasBd() {
        return contaRepository.findAll().stream().map(ContaDTO::new).toList();
    }

    public Optional<ContaDTO> getContaById(Integer id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (conta.isPresent()) {
            return Optional.ofNullable(ContaDTO.create(conta.get()));
        }
        throw new EntityNotFoundException("A conta não foi encontrada");
    }

    public Optional<Conta> getContaByEmail(String email) {
        Optional<Conta> conta = contaRepository.findByEmail(email);
        if (conta.isPresent()) {
            return conta;
        }
        throw new EntityNotFoundException("A conta não pertence a nenhum email no bd");
    }

    public boolean getUserByLogin(SolicitacaoDeLoginDTO loginAuth) throws CredentialException {
        return validacaoLogin.validar(loginAuth);
    }

    public void criarConta(Conta conta) {
        if (!validacaoEmail.validar(conta)) {
            conta.setSenha(passwordEncoder.encode(conta.getSenha()));
            contaRepository.save(conta);
        }
    }

    public Conta atualizarConta(Conta conta, Integer id) {
        Optional<Conta> optionalConta = contaRepository.findById(id);
        if (optionalConta.isPresent() && validacaoEmail.validar(conta)) {
            Conta db = optionalConta.get();
            db.setNome(conta.getNome());
            db.setSobrenome(conta.getSobrenome());
            db.setTelefone(conta.getTelefone());
            db.setEmail(conta.getEmail());
            contaRepository.save(db);
            return db;
        }
        throw new EntityNotFoundException("Conta não encontrada");
    }

    public void saveTotal(Integer id, Transacao transacao) {
        Optional<ContaDTO> optionalConta = getContaById(id);
        BigDecimal novoTotal;
        if (transacao.getTipo() == TipoTransacao.GASTO) {
            novoTotal = optionalConta.get().getTotal().subtract(transacao.getValor());
        } else {
            novoTotal = optionalConta.get().getTotal().add(transacao.getValor());
        }
        Conta conta = new Conta(optionalConta.get(), novoTotal);
        contaRepository.save(conta);
    }

    public void delete(Integer id) {
        if (validacaoContaExistente.validar(id)) {
            contaRepository.deleteById(id);
        }
    }
}
