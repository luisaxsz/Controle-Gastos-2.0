package ControleGastos.ControleGastos.Domain.view;

import ControleGastos.ControleGastos.Domain.Model.TipoTransacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Subselect;

import java.math.BigDecimal;

@Entity
@Data
@Subselect("""
    SELECT
      ID AS ID,
      TIPO AS TIPO,
      VALOR AS VALOR,
      DESCRICAO AS DESCRICAO
    FROM VW_LIST_TRANSACAO
""")
public class TransacaoListView {

  @Id
  private Integer id;
  @Column(name = "TIPO")
  private TipoTransacao tipo;
  @Column(name = "VALOR")
  private BigDecimal valor;
  @Column(name = "DESCRICAO")
  private String descricao;

}
