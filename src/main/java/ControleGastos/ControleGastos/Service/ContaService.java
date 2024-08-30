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
    private BigDecimal novoTotal;

    public List<ContaDTO> getContasBd() {
        return contaRepository.findAll().stream().map(ContaDTO::new).toList();
    }

    public Optional<ContaDTO> getContaById(Integer id) {
        Optional<Conta> conta = contaRepository.findById(id);
        if (validacaoContaExistente.validar(id)) {
            return Optional.ofNullable(ContaDTO.create(conta.get()));
        }
        throw new EntityNotFoundException("A conta não foi encontrada");
    }

    public boolean getUserByLogin(SolicitacaoDeLoginDTO loginAuth) throws CredentialException {
        return validacaoLogin.validar(loginAuth);
    }

    public Conta criarConta(Conta conta) {
        if (validacaoEmail.validar(conta)) {
            Conta novaConta = new Conta(conta.getNome(), conta.getSobrenome(), conta.getTelefone(), passwordEncoder.encode(conta.getSenha()), conta.getEmail());
            contaRepository.save(novaConta);
            return novaConta;
        }
        throw new IllegalArgumentException("Não foi possível criar a conta");
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
        Conta conta = contaRepository.getReferenceById(id);
        BigDecimal novoTotal = BigDecimal.valueOf(0);
        if (transacao.getTipo() == TipoTransacao.GASTO && validacaoContaExistente.validar(id)) {
            novoTotal = conta.getTotal().subtract(transacao.getValor());
        } else if (transacao.getTipo() == TipoTransacao.LUCRO && validacaoContaExistente.validar(id)) {
            novoTotal = conta.getTotal().add(transacao.getValor());
        }
        conta.setTotal(novoTotal);
        contaRepository.save(conta);
    }

    public void delete(Integer id) {
        if (validacaoContaExistente.validar(id)) {
            contaRepository.deleteById(id);
        }
    }
}
