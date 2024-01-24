package ControleGastos.ControleGastos.Conta;

import ControleGastos.ControleGastos.Transacao.Transacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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
    private BCryptPasswordEncoder passwordEncoder;

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

    public Optional<Conta> getContaByEmail(String email){
        Optional<Conta> conta = contaRepository.findByEmail(email);
        if(conta.isPresent()){
            return conta;
        }else {
            throw new EntityNotFoundException("A conta não pertence a nenhum email no bd");
        }
    }

    public boolean getUserByLogin(ContaDTO loginAuth) throws CredentialException {
        Optional<Conta> contaOptional = contaRepository.findByEmail(loginAuth.getEmail());
        //if (!isValidEmail(loginAuth.getEmail())) throw new IllegalArgumentException("Email Inválido");
        if (contaOptional.isPresent() && passwordEncoder.matches(loginAuth.getSenha(), contaOptional.get().getSenha())) {
            return true;
        } else {
            throw new CredentialException("Credenciais Inválidas");
        }
    }

    public Conta criarConta(Conta conta){
        Optional<Conta> emailUser = contaRepository.findByEmail(conta.getEmail());
        Assert.isNull(conta.getId(), "Não foi possível criar conta");
        if (!emailUser.isPresent()) {
            String senhaCrip = passwordEncoder.encode(conta.getSenha());
            conta.setSenha(senhaCrip);
            Assert.isNull(conta.getTotal(), "Total tem que está em branco");
            conta.setTotal(BigDecimal.ZERO);
            return contaRepository.save(conta);
        } else {
            throw new IllegalArgumentException("Conta Existente");
        }
    }

    public Conta atualizarConta(Conta conta, Integer id) {
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

   /* public static boolean isValidEmail(String email) {
        // Verifica se o endereço de e-mail está vazio
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Verifica se o endereço de e-mail contém o caractere `@`
        if (!email.contains("@")) {
            return false;
        }
        // Verifica se o domínio do endereço de e-mail é válido
        int index = email.lastIndexOf("@");
        String dominio = email.substring(index + 1);
        return dominio.matches("[a-zA-Z0-9-]+\\.[a-zA-Z]+");
    }*/

}
