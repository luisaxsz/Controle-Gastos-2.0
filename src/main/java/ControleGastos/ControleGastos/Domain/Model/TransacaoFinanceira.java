package ControleGastos.ControleGastos.Domain.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACAO_FINANCEIRA")
public class TransacaoFinanceira {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACAO_FINANCEIRA")
  @SequenceGenerator(name = "SEQ_TRANSACAO_FINANCEIRA", sequenceName = "SEQ_TRANSACAO_FINANCEIRA", allocationSize = 1)
  private Long id;

  @Column(name = "DATA", nullable = false)
  private LocalDate date;

  @Column(name = "DESCRICAO")
  private String descricao;









}
