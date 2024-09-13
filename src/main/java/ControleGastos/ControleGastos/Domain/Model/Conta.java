package ControleGastos.ControleGastos.Domain.Model;

import ControleGastos.ControleGastos.Domain.Databind.ContaDatabind;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
  private String email;
  private BigDecimal total;

//  private String senha;
//  @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
//  private List<Transacao> transacaoList;
}

