package br.com.wsb.DonJose.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsb.DonJose.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
