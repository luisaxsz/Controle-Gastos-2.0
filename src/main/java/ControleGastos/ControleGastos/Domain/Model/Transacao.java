package ControleGastos.ControleGastos.Domain.Model;

import ControleGastos.ControleGastos.Domain.Databind.ContaDatabind;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACAO")
  @SequenceGenerator(name = "SEQ_TRANSACAO", sequenceName = "SEQ_TRANSACAO", allocationSize = 1)
  private Integer id;

  @Enumerated(EnumType.STRING)
  private TipoTransacao tipo;
  private BigDecimal valor;
  private String descricao;

  @ManyToOne(optional = false)
  @JoinColumn(name = "CONTA_ID", nullable = false)
  @JsonSerialize(using = ContaDatabind.IdSerializer.class)
  @JsonDeserialize(using = ContaDatabind.IdDeserializer.class)
  private Conta conta;
}
