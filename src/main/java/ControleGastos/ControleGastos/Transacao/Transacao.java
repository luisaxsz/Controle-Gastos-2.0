package ControleGastos.ControleGastos.Transacao;

import ControleGastos.ControleGastos.Conta.Conta;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
