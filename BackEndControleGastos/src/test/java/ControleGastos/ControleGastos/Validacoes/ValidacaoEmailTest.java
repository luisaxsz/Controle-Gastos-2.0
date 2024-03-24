package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
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
class ValidacaoEmailTest {

    @InjectMocks
    private ValidacaoEmail validacaoEmail;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private Conta conta;

    @Test
    void validarContaComEmailExistente(){
        given(contaRepository.findByEmail(conta.getEmail())).willReturn(Optional.of(conta));
        assertThrows(IllegalArgumentException.class, () -> validacaoEmail.validar(conta));
    }

    @Test
    void validarContaComEmailNaoExistente(){
        given(contaRepository.findByEmail(conta.getEmail())).willReturn(Optional.empty());
        assertDoesNotThrow(() -> validacaoEmail.validar(conta));
    }




}