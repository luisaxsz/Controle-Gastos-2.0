package ControleGastos.ControleGastos.Controller;

import ControleGastos.ControleGastos.Domain.view.ContaListView;
import ControleGastos.ControleGastos.Repository.ContaListViewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/conta-list-view")
@AllArgsConstructor
public class ContaListViewController {

  private final ContaListViewRepository contaListViewRepository;

  @GetMapping()
  public ResponseEntity<List<ContaListView>> getAllContas() {
    return ResponseEntity.ok(contaListViewRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContaListView> getContaById(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(contaListViewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Conta não encontrada")));
  }
}
