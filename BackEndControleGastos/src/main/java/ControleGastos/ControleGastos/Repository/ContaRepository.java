package ControleGastos.ControleGastos.Repository;

import ControleGastos.ControleGastos.Model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta,Integer> {
    public Optional<Conta> findByEmail(String email);
}
