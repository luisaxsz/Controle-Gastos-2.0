package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.Model.TipoTransacao;
import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Service.TransacaoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TrasacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/allConta/{idConta}")
    public ResponseEntity<?> getAll(@PathVariable("idConta") Integer idConta) {
        return ResponseEntity.ok(transacaoService.getTransacoesBd(idConta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransacaoByid(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(transacaoService.getTransacaoById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> getByTipo(@PathVariable("tipo") TipoTransacao tipo) {
        try {
            return ResponseEntity.ok(transacaoService.getTransaçõesByTipo(tipo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{idConta}")
    public ResponseEntity<?> postTransação(@PathVariable("idConta") Integer idConta, @RequestBody Transacao transacao) {
        try {
            transacaoService.adicionarTransacao(idConta, transacao);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/conta/{idConta}")
    public ResponseEntity<?> putTransação(@PathVariable("id") Integer id, @PathVariable("idConta") Integer idConta, @RequestBody Transacao transacao) {
        try {
            return ResponseEntity.ok(transacaoService.atualizarTransacao(transacao, id, idConta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransacao(@PathVariable("id") Integer id) {
        try {
            transacaoService.deleteTransacao(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
