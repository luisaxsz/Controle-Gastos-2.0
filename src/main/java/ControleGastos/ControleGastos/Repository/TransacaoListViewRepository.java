package ControleGastos.ControleGastos.Repository;

import ControleGastos.ControleGastos.Domain.view.TransacaoListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoListViewRepository extends JpaRepository<TransacaoListView, Integer> {
}
