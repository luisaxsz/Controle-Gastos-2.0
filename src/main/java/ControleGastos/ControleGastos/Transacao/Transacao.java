package ControleGastos.ControleGastos.Transacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private String descricao;

    public enum TipoTransacao {
        GASTO,
        LUCRO
    }
}
