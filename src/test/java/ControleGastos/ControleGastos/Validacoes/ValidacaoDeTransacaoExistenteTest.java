package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidacaoDeTransacaoExistenteTest {

    @InjectMocks
    private ValidacaoDeTransacaoExistente validacaoDeTransacaoExistente;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private Transacao transacao;

    @Test
    void validacaoParaTransacaoExistente(){
        given(transacaoRepository.findById(transacao.getId())).willReturn(Optional.of(transacao));
        assertDoesNotThrow(() -> validacaoDeTransacaoExistente.validar(transacao.getId()));
    }

    @Test
    void validacaoParaTransacaoNaoExistente(){
        given(transacaoRepository.findById(transacao.getId())).willReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> validacaoDeTransacaoExistente.validar(transacao.getId()));
    }
}
