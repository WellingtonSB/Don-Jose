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
	
	@Autowired
    private CepService cepService;
	
	
	public Optional<Cliente> CadastrarCliente(Cliente cliente) {
		
		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent() && cliente.getId() == 0) {
			return null;
		}
		
		Cliente infoEndereco = cepService.buscaEnderecoPorCep(cliente.getCep());
		cliente.setBairro(infoEndereco.getBairro());
		cliente.setLocalidade(infoEndereco.getLocalidade());
		cliente.setUf(infoEndereco.getUf());
		cliente.setLogradouro(infoEndereco.getLogradouro());

		if (cliente.getComplemento() == null) {
			cliente.setComplemento(infoEndereco.getComplemento());
		}
		
		cliente.setNumero(infoEndereco.getNumero());
	
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
				clienteLogin.get().setSenha(cliente.get().getSenha());
				clienteLogin.get().setFoto(cliente.get().getFoto());
				clienteLogin.get().setNome(cliente.get().getNome());
				clienteLogin.get().setBairro(cliente.get().getBairro());
				clienteLogin.get().setCep(cliente.get().getCep());
				clienteLogin.get().setComplemento(cliente.get().getComplemento());
				clienteLogin.get().setCpf(cliente.get().getCpf());
				clienteLogin.get().setUf(cliente.get().getUf());
				clienteLogin.get().setCelular(cliente.get().getCelular());
				clienteLogin.get().setId(cliente.get().getId());
				clienteLogin.get().setNumero(cliente.get().getNumero());
				clienteLogin.get().setPedidos(cliente.get().getPedidos());
				clienteLogin.get().setCarrinho(cliente.get().getCarrinho());

				return clienteLogin;

			}
		}

		return null;
	}

}