package ControleGastos.ControleGastos.Transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Integer> {

    public List<Transacao> getTransacoesByTipo(Transacao.TipoTransacao tipo);
}
