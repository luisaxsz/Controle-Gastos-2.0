package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.Domain.view.TransacaoListView;
import ControleGastos.ControleGastos.Repository.TransacaoListViewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transacao-list-view")
@AllArgsConstructor
public class TransacaoListViewController {

  private final TransacaoListViewRepository transacaoListViewRepository;

  @GetMapping()
  public ResponseEntity<List<TransacaoListView>> getAllContas() {
    return ResponseEntity.ok(transacaoListViewRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TransacaoListView> getContaById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(transacaoListViewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada")));
  }
}
