package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.Domain.Model.Transacao;
import ControleGastos.ControleGastos.Repository.TransacaoRepository;
import ControleGastos.ControleGastos.Service.AdicionarTransacaoService;
import ControleGastos.ControleGastos.Service.AtualizarTransacaoService;
import ControleGastos.ControleGastos.Service.Deprecated.TransacaoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
@AllArgsConstructor
@CrossOrigin(origins = {"*"}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class TrasacaoController {

  private final TransacaoService transacaoService;
  private final AtualizarTransacaoService atualizarTransacaoService;
  private final AdicionarTransacaoService adicionarTransacaoService;
  private final TransacaoRepository transacaoRepository;

  @GetMapping()
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(transacaoRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getTransacaoByid(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(transacaoRepository.findById(id).orElseThrow(EntityNotFoundException::new));
  }

//  @GetMapping("/tipo/{tipo}")
//  public ResponseEntity<?> getByTipo(@PathVariable("tipo") TipoTransacao tipo) {
//    return ResponseEntity.ok(transacaoRepository.find);
//  }

  @PostMapping("/{idConta}")
  public ResponseEntity<?> postTransação(@PathVariable("idConta") Integer idConta, @RequestBody Transacao transacao) {
    try {
      adicionarTransacaoService.adicionarTransacao(transacao);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> putTransação(@PathVariable("id") Integer id, @RequestBody Transacao transacao) {
    try {
      return ResponseEntity.ok(atualizarTransacaoService.atualizarTransacao(transacao, id));
    } catch (RuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTransacao(@PathVariable("id") Integer id) {
    Transacao transacao = transacaoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    transacaoRepository.delete(transacao);
    return ResponseEntity.ok().build();
  }


//    @GetMapping("/allConta/{idConta}")
//    public ResponseEntity<?> getAll(@PathVariable("idConta") Integer idConta) {
//        return ResponseEntity.ok(transacaoService.getTransacoesBd(idConta));
//    }

}
