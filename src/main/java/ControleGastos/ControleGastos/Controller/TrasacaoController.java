package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.Model.Transacao;
import ControleGastos.ControleGastos.Service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,RequestMethod.PUT})
public class TrasacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("allConta/{idConta}")
    public ResponseEntity<?> getAll(@PathVariable("idConta") Integer idConta){
        return ResponseEntity.ok(transacaoService.getTransacoesBd(idConta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransacaoByid(@PathVariable("id") Integer id){
        return ResponseEntity.ok(transacaoService.getTransacaoById(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> getByTipo(@PathVariable("tipo") Transacao.TipoTransacao tipo){
        return ResponseEntity.ok(transacaoService.getTransaçõesByTipo(tipo));
    }

    @PostMapping("/{idConta}")
    public ResponseEntity<?> postTransação(@PathVariable("idConta") Integer idConta, @RequestBody Transacao transacao){
        return ResponseEntity.ok(transacaoService.adicionarTransacao(idConta,transacao));
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
