package ControleGastos.ControleGastos.Domain.Model;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
    @SequenceGenerator(name = "SEQ_CONTA", sequenceName = "SEQ_CONTA", allocationSize = 1)
    private Integer id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String senha;
    private String email;
    private BigDecimal total;
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transacao> transacaoList;

    public Conta() {
    }

    public Conta(Integer id, String nome, String sobrenome, String telefone, String senha, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.senha = senha;
        this.email = email;
        this.total = BigDecimal.ZERO;
    }

    public Conta(String nome, String sobrenome, String telefone, String senha, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.senha = senha;
        this.email = email;
        this.total = BigDecimal.valueOf(0);
    }
    public Conta(ContaDTO contaDTO, BigDecimal novoTotal) {
        this.id = contaDTO.getId();
        this.nome = contaDTO.getNome();
        this.sobrenome = contaDTO.getSobrenome();
        this.total = novoTotal;
    }
}

