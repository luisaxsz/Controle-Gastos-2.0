package ControleGastos.ControleGastos.Conta;

import ControleGastos.ControleGastos.Transacao.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contas")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @GetMapping()
    public ResponseEntity<?> getAllContas(){
        return ResponseEntity.ok(contaService.getContasBd());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContasById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(contaService.getContaById(id));
    }

    @PostMapping()
    public ResponseEntity<?> postConta(@RequestBody Conta conta){
        Conta l = contaService.criarConta(conta);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/transacao/{id}")
    public ResponseEntity<?> transacao(@PathVariable("id") Integer id, @RequestBody Transacao transacao){
        return ResponseEntity.ok(contaService.saveTotal(id,transacao));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarConta(@RequestBody Conta conta, @PathVariable("id") Integer id){
        return ResponseEntity.ok(contaService.atualizarTransacao(conta,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        contaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
