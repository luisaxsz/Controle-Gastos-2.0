package ControleGastos.ControleGastos.Transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TrasacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(transacaoService.getTransacoesBd());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> getByTipo(@PathVariable("tipo")Transacao.TipoTransacao tipo){
        return ResponseEntity.ok(transacaoService.getTransaçõesByTipo(tipo));
    }

    @PostMapping("/{idConta}")
    public ResponseEntity<?> postTransação(@PathVariable("idConta") Integer id, @RequestBody Transacao transacao){
        return ResponseEntity.ok(transacaoService.adicionarTransacao(id,transacao));
    }

    @PutMapping("/{id}/conta/{idConta}")
    public ResponseEntity<?> putTransação(@PathVariable("id") Integer id,@PathVariable("idConta") Integer idConta, @RequestBody Transacao transacao){
        return ResponseEntity.ok(transacaoService.atualizarTransacao(transacao,id,idConta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransacao(@PathVariable("id") Integer id){
        transacaoService.deleteTransacao(id);
        return ResponseEntity.ok().build();
    }
}
