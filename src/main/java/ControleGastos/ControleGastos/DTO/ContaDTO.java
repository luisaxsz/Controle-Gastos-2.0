package ControleGastos.ControleGastos.DTO;

import ControleGastos.ControleGastos.Domain.Model.Conta;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@Deprecated
public class ContaDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private BigDecimal total;


    public ContaDTO() {
    }

    public ContaDTO(Integer id, String nome, String sobrenome, BigDecimal total) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.total = total;
    }

    public ContaDTO(Conta conta){
        this.id = conta.getId();
        this.nome = conta.getNome();
        this.sobrenome = conta.getSobrenome();
        this.total = conta.getTotal();
    }

    public static ContaDTO create(Conta c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, ContaDTO.class);
    }

}
