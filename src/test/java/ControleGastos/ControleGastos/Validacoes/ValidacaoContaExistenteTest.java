package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoContaExistenteTest {
    @InjectMocks
    private ValidacaoContaExistente validacaoContaExistente;
    @Mock
    private Conta conta;

    @Mock
    private ContaRepository contaRepository;

    @Test
    void validacaoParaContaExistente(){
        given(contaRepository.findById(conta.getId())).willReturn(Optional.of(conta));
        assertDoesNotThrow(() -> validacaoContaExistente.validar(conta.getId()));
    }
    @Test
    void validacaoParaContaNaoExistente(){
        given(contaRepository.findById(conta.getId())).willReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class , () -> validacaoContaExistente.validar(conta.getId()));
    }

}