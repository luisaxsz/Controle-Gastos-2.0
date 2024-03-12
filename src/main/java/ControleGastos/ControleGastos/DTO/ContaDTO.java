package ControleGastos.ControleGastos.DTO;

import ControleGastos.ControleGastos.Model.Conta;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
public class ContaDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private BigDecimal total;


    public ContaDTO() {
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
