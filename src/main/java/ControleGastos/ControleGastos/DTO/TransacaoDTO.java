package ControleGastos.ControleGastos.DTO;

import ControleGastos.ControleGastos.Domain.Model.TipoTransacao;
import ControleGastos.ControleGastos.Domain.Model.Transacao;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@Deprecated
public class TransacaoDTO {

    private Integer id;
    private TipoTransacao tipo;
    private BigDecimal valor;
    private String descricao;

    public TransacaoDTO() {
    }

    public TransacaoDTO(Transacao transacao) {
        this.id = transacao.getId();
        this.tipo = transacao.getTipo();
        this.valor = transacao.getValor();
        this.descricao = transacao.getDescricao();
    }

    public static TransacaoDTO create(Transacao t) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, TransacaoDTO.class);
    }

}
