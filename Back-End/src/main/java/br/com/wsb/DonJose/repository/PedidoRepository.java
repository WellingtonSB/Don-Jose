package br.com.wsb.DonJose.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsb.DonJose.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	public List<Pedido>findByNumeroPedido(int numeroPedido);
}
