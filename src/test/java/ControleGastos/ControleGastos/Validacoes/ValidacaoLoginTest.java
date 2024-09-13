package ControleGastos.ControleGastos.Validacoes;

import ControleGastos.ControleGastos.DTO.SolicitacaoDeLoginDTO;
import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Repository.ContaRepository;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ValidacaoLoginTest {

    @InjectMocks
    private ValidacaoLogin validacaoLogin;

    @Mock
    private Conta conta;

    @Mock
    private SolicitacaoDeLoginDTO solicitacaoDeLoginDTO;

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;


//    @Test
//    void validacaoDeLoginBemSucedido(){
//        given(contaRepository.findByEmail(solicitacaoDeLoginDTO.getEmail())).willReturn(Optional.of(conta));
//        given(passwordEncoder.matches(solicitacaoDeLoginDTO.getSenha(), conta.getSenha())).willReturn(true);
//        assertDoesNotThrow(() -> validacaoLogin.validar(solicitacaoDeLoginDTO));
//    }
//
//    @Test
//    void validacaoLoginDeContaNaoExistente(){
//        given(contaRepository.findByEmail(solicitacaoDeLoginDTO.getEmail())).willReturn(Optional.empty());
//        given(passwordEncoder.matches(solicitacaoDeLoginDTO.getSenha(), conta.getSenha())).willReturn(true);
//        assertThrows(CredentialException.class ,() -> validacaoLogin.validar(solicitacaoDeLoginDTO));
//    }
//    @Test
//    void validacaoLoginComCredenciaisInvalidas(){
//        given(contaRepository.findByEmail(solicitacaoDeLoginDTO.getEmail())).willReturn(Optional.of(conta));
//        given(passwordEncoder.matches(solicitacaoDeLoginDTO.getSenha(), conta.getSenha())).willReturn(false);
//        assertThrows(CredentialException.class ,() -> validacaoLogin.validar(solicitacaoDeLoginDTO));
//    }


}
