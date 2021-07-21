package br.com.wsb.DonJose.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.ClienteLogin;
import br.com.wsb.DonJose.model.ListaDeDesejos;
import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.repository.ListaDeDesejosRepository;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.service.exceptions.DataIntegrityException;
import br.com.wsb.DonJose.service.exceptions.ObjectNotFoundException;
import br.com.wsb.DonJose.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ListaDeDesejosRepository listaDeDesejosRepository;

	@Autowired
	private CepService cepService;

	public Cliente find(long id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Optional<Cliente> CadastrarCliente(Cliente cliente) {

		if (clienteRepository.findByUsuario(cliente.getUsuario()).isPresent() && cliente.getId() == 0) {
			return null;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(cliente.getSenha());
		cliente.setSenha(senhaEncoder);
		ListaDeDesejos listaDeDesejos = new ListaDeDesejos();
		clienteRepository.save(cliente);
		listaDeDesejos.setCliente(cliente);
		listaDeDesejosRepository.save(listaDeDesejos);
		return Optional.of(clienteRepository.save(cliente));

	}

	public Optional<ClienteLogin> Logar(Optional<ClienteLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Cliente> cliente = clienteRepository.findByUsuario(user.get().getUsuario());

		if (cliente.isPresent()) {
			if (encoder.matches(user.get().getSenha(), cliente.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setId(cliente.get().getId());
				user.get().setNome(cliente.get().getNome());
				user.get().setEmail(cliente.get().getEmail());
				user.get().setSenha(cliente.get().getSenha());
				user.get().setListaDeDesejos(cliente.get().getListaDeDesejos());

				return user;
			}
		}

		return null;
	}

	public void delete(long id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
}
