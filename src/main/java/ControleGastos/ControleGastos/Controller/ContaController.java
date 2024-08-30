package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.DTO.ContaDTO;
import ControleGastos.ControleGastos.DTO.SolicitacaoDeLoginDTO;
import ControleGastos.ControleGastos.Service.ContaService;
import ControleGastos.ControleGastos.Model.Conta;
import ControleGastos.ControleGastos.Model.Transacao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ContaController {
    @Autowired
    private ContaService contaService;

    @GetMapping()
    public ResponseEntity<List<ContaDTO>> getAllContas() {
        return ResponseEntity.ok(contaService.getContasBd());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContaDTO>> getContasById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(contaService.getContaById(id));
    }

    @PostMapping()
    public ResponseEntity<?> postConta(@RequestBody Conta conta) {
        try {
            contaService.criarConta(conta);
            return ResponseEntity.status(201).body("Conta Criada Com Sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userAuth(@RequestBody SolicitacaoDeLoginDTO loginAuth) throws CredentialException {
        try {
            return ResponseEntity.ok(contaService.getUserByLogin(loginAuth));
        } catch (CredentialException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/transacao/{id}")
    public ResponseEntity<?> transacao(@PathVariable("id") Integer id, @RequestBody Transacao transacao) {
        try {
            contaService.saveTotal(id, transacao);
            return ResponseEntity.status(201).body("Transação Adicionada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarConta(@RequestBody Conta conta, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(contaService.atualizarConta(conta, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            contaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
