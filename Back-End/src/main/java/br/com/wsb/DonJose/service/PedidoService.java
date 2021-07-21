package br.com.wsb.DonJose.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido find(long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
