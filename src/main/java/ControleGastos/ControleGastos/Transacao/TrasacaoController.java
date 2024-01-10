package ControleGastos.ControleGastos.Transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
