package br.com.wsb.DonJose.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.Carrinho;
import br.com.wsb.DonJose.model.Cliente;
import br.com.wsb.DonJose.model.ClienteLogin;
import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.repository.CarrinhoRepository;
import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.repository.PedidoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	public Optional<Cliente> CadastrarCliente(Cliente cliente) {

		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent() && cliente.getId() == 0) {
			return null;

		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(cliente.getSenha());
		cliente.setSenha(senhaEncoder);

		Pedido pedido = new Pedido();

		Carrinho carrinho = new Carrinho();

		clienteRepository.save(cliente);

		pedido.setCliente(cliente);

		carrinho.setCliente(cliente);

		pedidoRepository.save(pedido);
		carrinhoRepository.save(carrinho);

		return Optional.of(clienteRepository.save(cliente));

	}

	/* LOGA USUARIO NO SISTEMA */
	public Optional<ClienteLogin> Logar(Optional<ClienteLogin> clienteLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Cliente> cliente = clienteRepository.findByEmail(clienteLogin.get().getEmail());

		if (cliente.isPresent()) {

			if (encoder.matches(clienteLogin.get().getSenha(), cliente.get().getSenha())) {

				String auth = clienteLogin.get().getEmail() + ":" + clienteLogin.get().getSenha();

				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

				String authHeader = "Basic " + new String(encodedAuth);

				clienteLogin.get().setToken(authHeader);
				clienteLogin.get().setEmail(cliente.get().getEmail());
				clienteLogin.get().setCelular(cliente.get().getCelular());
				clienteLogin.get().setFoto(cliente.get().getFoto());
				clienteLogin.get().setNome(cliente.get().getNome());
				clienteLogin.get().setPedidos(cliente.get().getPedidos());
				clienteLogin.get().setCarrinho(cliente.get().getCarrinho());

				return clienteLogin;

			}
		}

		return null;
	}

}