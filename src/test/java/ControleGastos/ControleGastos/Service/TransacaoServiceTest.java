package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.DTO.TransacaoDTO;
import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoDeTransacaoExistente;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;
    @Mock
    private TransacaoRepository transacaoRepository;
    @Mock
    private Transacao transacao;
    @Mock
    private ContaRepository contaRepository;
    @Mock
    private ContaService contaService;
    @Mock
    private ValidacaoContaExistente validacaoContaExistente;
    @Mock
    private TransacaoDTO trasacaoDto;
    @Mock
    private Conta conta;

    @Mock
    private ValidacaoDeTransacaoExistente validacaoDeTransacaoExistente;

    @Test
    void deveriaBuscarTransacaoPorId() {
        given(transacaoRepository.findById(transacao.getId())).willReturn(Optional.of(transacao));
        given(validacaoDeTransacaoExistente.validar(transacao.getId())).willReturn(true);
        Optional<TransacaoDTO> transacaoDto = transacaoService.getTransacaoById(transacao.getId());
        assertNotNull(transacaoDto);
        assertTrue(transacaoDto.isPresent());
        assertEquals(transacaoDto.get().getId(), transacao.getId());
        assertEquals(transacaoDto.get().getTipo(), transacao.getTipo());
        assertEquals(transacaoDto.get().getValor(), transacao.getValor());
        assertEquals(transacaoDto.get().getDescricao(), transacao.getDescricao());
    }

    @Test
    void naoDeveriaBuscarTransacaoPorId(){
        given(transacaoRepository.findById(transacao.getId())).willReturn(Optional.empty());
        given(validacaoDeTransacaoExistente.validar(transacao.getId())).willThrow(new EntityNotFoundException("A conta não foi encontrada"));
        assertThrows(EntityNotFoundException.class, () -> transacaoService.getTransacaoById(transacao.getId()));
    }

    @Test
    void deveriaRetornarListaDeTransacoesPorTipo(){
        given(transacaoRepository.getTransacoesByTipo(transacao.getTipo())).willReturn(List.of(transacao));
        List<TransacaoDTO> listaTransacao = transacaoService.getTransaçõesByTipo(transacao.getTipo());
        assertNotNull(listaTransacao);
        assertEquals(listaTransacao.get(0).getTipo(), transacao.getTipo());
    }

    @Test
    void deveriaAdicionarNovaTransacao(){
        given(validacaoContaExistente.validar(conta.getId())).willReturn(true);
        Transacao novaTransacao = transacaoService.adicionarTransacao(conta.getId(),transacao);
        assertNotNull(novaTransacao);
        then(transacaoRepository).should().save(novaTransacao);
    }

    @Test
    void naoDeveriaAdicionarNovaTransacao(){
        given(validacaoContaExistente.validar(conta.getId())).willThrow(new EntityNotFoundException("Conta não existe"));
        assertThrows(EntityNotFoundException.class, () -> transacaoService.adicionarTransacao(conta.getId(), transacao));
    }

    @Test
    void deveriaAtualizarTransacao(){
        given(validacaoDeTransacaoExistente.validar(transacao.getId())).willReturn(true);
        given(transacaoRepository.getReferenceById(transacao.getId())).willReturn(transacao);
        Transacao transacaoAtualizada = transacaoService.atualizarTransacao(transacao, transacao.getId(), conta.getId());
        then(transacaoRepository).should().save(transacaoAtualizada);
    }

    @Test
    void naoDeveriaAtualizarTransacao(){
        given(validacaoDeTransacaoExistente.validar(transacao.getId())).willReturn(false);
        given(transacaoRepository.getReferenceById(transacao.getId())).willReturn(null);
        assertThrows(RuntimeException.class, () -> transacaoService.atualizarTransacao(transacao, transacao.getId(), conta.getId()));
    }
    @Test
    void deveriaApagarTransacao(){
        given(validacaoDeTransacaoExistente.validar(transacao.getId())).willReturn(true);
        transacaoService.deleteTransacao(transacao.getId());
        then(transacaoRepository).should().deleteById(transacao.getId());
    }

    @Test
    void naoDeveriaApagarTransacao(){
        given(validacaoDeTransacaoExistente.validar(transacao.getId())).willThrow(new EntityNotFoundException("Transacao não existente"));
        assertThrows(EntityNotFoundException.class, () -> transacaoService.deleteTransacao(transacao.getId()));
    }


}
