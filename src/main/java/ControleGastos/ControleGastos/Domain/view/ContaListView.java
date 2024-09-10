package ControleGastos.ControleGastos.Domain.view;

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
      NOME AS NOME,
      SOBRENOME AS SOBRENOME,
      TOTAL AS TOTAL
    FROM VW_LIST_CONTA
""")
public class ContaListView {

  @Id
  private Integer id;
  @Column(name = "NOME")
  private String nome;
  @Column(name = "SOBRENOME")
  private String sobrenome;
  @Column(name = "TOTAL")
  private BigDecimal total;

}
