package ControleGastos.ControleGastos.Repository;

import ControleGastos.ControleGastos.Domain.view.ContaListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaListViewRepository extends JpaRepository<ContaListView, Integer> {
}
