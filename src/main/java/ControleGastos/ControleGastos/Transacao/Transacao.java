package ControleGastos.ControleGastos.Transacao;

import ControleGastos.ControleGastos.Conta.Conta;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public enum TipoTransacao {
        GASTO,
        LUCRO
    }
}
