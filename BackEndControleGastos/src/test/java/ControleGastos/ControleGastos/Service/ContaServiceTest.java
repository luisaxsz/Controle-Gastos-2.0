package ControleGastos.ControleGastos.Service;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import ControleGastos.ControleGastos.DTO.SolicitacaoDeLoginDTO;
import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Validacoes.ValidacaoContaExistente;
import ControleGastos.ControleGastos.Validacoes.ValidacaoEmail;
import ControleGastos.ControleGastos.Validacoes.ValidacaoLogin;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;
    @Mock
    private Conta conta;
    @Mock
    private ValidacaoContaExistente validacaoContaExistente;

    @Mock
    private ValidacaoLogin validacaoLogin;
    @Mock
    private ValidacaoEmail validacaoEmail;

    @Mock
    private SolicitacaoDeLoginDTO solicitacaoLoginDto;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;


    @Test
    void deveListarTodasAsContasDTOExistentes() {
        //Act
        contaService.getContasBd();
        //Assert
        then(contaRepository).should().findAll();
    }

    @Test
    void deveriaRetornar200AoBuscarContaPorId() {
        //arrange
        given(contaRepository.findById(conta.getId())).willReturn(Optional.of(conta));
        given(validacaoContaExistente.validar(conta.getId())).willReturn(true);

        Optional<ContaDTO> dto = contaService.getContaById(conta.getId());

        assertEquals(conta.getId(), dto.get().getId());
        assertEquals(conta.getNome(), dto.get().getNome());
        assertEquals(conta.getSobrenome(), dto.get().getSobrenome());
        assertEquals(conta.getTotal(), dto.get().getTotal());

        then(validacaoContaExistente).should().validar(conta.getId());
        then(contaRepository).should().findById(conta.getId());
    }

    @Test
    void deveriaRetornar404AoBuscarContaPorId() {
        given(contaRepository.findById(conta.getId())).willReturn(Optional.empty());
        given(validacaoContaExistente.validar(conta.getId())).willReturn(false);

        assertThrows(EntityNotFoundException.class, () -> contaService.getContaById(conta.getId()));
    }

    @Test
    void deveriaRetornar200AoFazerLogin() throws CredentialException {
        given(validacaoLogin.validar(solicitacaoLoginDto)).willReturn(true);

        assertTrue(contaService.getUserByLogin(solicitacaoLoginDto));
        then(validacaoLogin).should().validar(solicitacaoLoginDto);
    }

    @Test
    void deveriaRetornar401AoFazerLogin() throws CredentialException {
        given(validacaoLogin.validar(solicitacaoLoginDto)).willReturn(false);

        assertFalse(contaService.getUserByLogin(solicitacaoLoginDto));
        then(validacaoLogin).should().validar(solicitacaoLoginDto);
    }

    @Test
    void deveriaCriarConta(){
        given(validacaoEmail.validar(conta)).willReturn(true);
        Conta novaConta = contaService.criarConta(conta);
        then(contaRepository).should().save(novaConta);
    }

    @Test
    void nãoDeveriaCriarNovaConta(){
        given(validacaoEmail.validar(conta)).willThrow(new IllegalArgumentException ("Email já existente"));
        assertThrows(IllegalArgumentException.class, () -> contaService.criarConta(conta));
    }

    @Test
    void deveriaAtualizarConta(){
        given(contaRepository.findById(conta.getId())).willReturn(Optional.of(conta));
        given(validacaoEmail.validar(conta)).willReturn(true);
        Conta contaAtualizada = contaService.atualizarConta(conta, conta.getId());
        then(contaRepository).should().save(contaAtualizada);
    }

    @Test
    void naoDeveriaAtualizarConta(){
        given(contaRepository.findById(conta.getId())).willReturn(Optional.empty());
        given(validacaoEmail.validar(conta)).willThrow(new IllegalArgumentException("Email já existente"));
        assertThrows(EntityNotFoundException.class, () -> contaService.atualizarConta(conta, conta.getId()));
    }

    @Test
    void deveriaApagarConta(){
        given(validacaoContaExistente.validar(conta.getId())).willReturn(true);
        contaService.delete(conta.getId());
        then(contaRepository).should().deleteById(conta.getId());
    }
}