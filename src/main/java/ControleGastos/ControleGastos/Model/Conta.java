package ControleGastos.ControleGastos.Model;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

@Entity
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String senha;
    private String email;
    private BigDecimal total;

    public Conta() {
    }

    public Conta(String nome, String sobrenome, String telefone, String senha, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.senha = senha;
        this.email = email;
        this.total = BigDecimal.ZERO;
    }
    public Conta(ContaDTO contaDTO, BigDecimal novoTotal) {
        this.id = contaDTO.getId();
        this.nome = contaDTO.getNome();
        this.sobrenome = contaDTO.getSobrenome();
        this.telefone = telefone;
        this.email = email;
        this.total = novoTotal;
    }
}

