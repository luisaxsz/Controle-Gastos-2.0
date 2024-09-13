package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.Repository.ContaRepository;
import ControleGastos.ControleGastos.Service.AtualizarContaService;
import ControleGastos.ControleGastos.Domain.Model.Conta;
import ControleGastos.ControleGastos.Service.CriarContaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
@AllArgsConstructor
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ContaController {

  private final CriarContaService criarContaService;
  private final AtualizarContaService atualizarConta;
  private final ContaRepository contaRepository;

  @GetMapping()
  public ResponseEntity<List<Conta>> getAllContas() {
    return ResponseEntity.ok(contaRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Conta> getContasById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(contaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada")));
  }

  @PostMapping()
  public ResponseEntity<?> postConta(@RequestBody Conta conta) {
    try {
      criarContaService.criarConta(conta);
      return ResponseEntity.status(201).body("Conta Criada Com Sucesso");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> atualizarConta(@RequestBody Conta conta, @PathVariable("id") Integer id) {
    return ResponseEntity.ok(atualizarConta.atualizarConta(conta, id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
    if (contaRepository.existsById(id)) {
      contaRepository.deleteById(id);
      return ResponseEntity.ok("Conta Deletada");
    } else {
      return ResponseEntity.badRequest().body("Conta não encontrada");
    }
  }


/**
 * Após a implementação do método de autenticação, o método de login foi removido
 */
//    @PostMapping("/login")
//    public ResponseEntity<?> userAuth(@RequestBody SolicitacaoDeLoginDTO loginAuth) throws CredentialException {
//        try {
//            return ResponseEntity.ok(contaService.getUserByLogin(loginAuth));
//        } catch (CredentialException e) {
//            return ResponseEntity.status(401).body(e.getMessage());
//        }
//    }

/**
 * Método refatorado apenas para Transação após a implementação deserialize do JSON
 */
//  @PostMapping("/transacao/{id}")
//  public ResponseEntity<?> transacao(@PathVariable("id") Integer id, @RequestBody Transacao transacao) {
//    try {
//      contaService.saveTotal(id, transacao);
//      return ResponseEntity.status(201).body("Transação Adicionada");
//    } catch (Exception e) {
//      return ResponseEntity.badRequest().body(e.getMessage());
//    }
//  }
}
