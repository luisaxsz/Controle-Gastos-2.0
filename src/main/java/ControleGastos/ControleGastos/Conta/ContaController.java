package ControleGastos.ControleGastos.Conta;

import ControleGastos.ControleGastos.Transacao.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT})
public class ContaController {
    @Autowired
    private ContaService contaService;

    @GetMapping()
    public ResponseEntity<List<Conta>> getAllContas(){
        return ResponseEntity.ok(contaService.getContasBd());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Conta>> getContasById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(contaService.getContaById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<Conta>> getContaByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(contaService.getContaByEmail(email));
    }
    @PostMapping()
    public ResponseEntity<?> postConta(@RequestBody Conta conta){
        return ResponseEntity.ok(contaService.criarConta(conta));
    }
    @PostMapping("/login")
    public ResponseEntity<?> userAuth(@RequestBody ContaDTO loginAuth) throws CredentialException {
        return ResponseEntity.ok(contaService.getUserByLogin(loginAuth));
    }

    @PostMapping("/transacao/{id}")
    public ResponseEntity<?> transacao(@PathVariable("id") Integer id, @RequestBody Transacao transacao){
        return ResponseEntity.ok(contaService.saveTotal(id,transacao));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarConta(@RequestBody Conta conta, @PathVariable("id") Integer id){
        return ResponseEntity.ok(contaService.atualizarConta(conta,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        contaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
