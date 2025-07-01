package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Domain.Model.Usuario;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ValidacaoUsuarioExistenteTest {
    @InjectMocks
    private ValidacaoContaExistente validacaoContaExistente;
    @Mock
    private Usuario conta;

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
