package br.com.wsb.DonJose.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.ClienteLogin;
import br.com.wsb.DonJose.model.ListaDeDesejos;
import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.repository.ListaDeDesejosRepository;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ListaDeDesejosRepository listaDeDesejosRepository;
	
	@Autowired
    private CepService cepService;
	

	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public Optional<Cliente> CadastrarCliente(Cliente cliente) {
		
		if (clienteRepository.findByUsuario(cliente.getUsuario()).isPresent() && cliente.getId() == 0) {
			System.out.println("cliente já existe");
			//fazer uma trativa de erro
			return null;
		}
		
		//viaCEP
		Cliente infoEndereco = cepService.buscaEnderecoPorCep(cliente.getCep());
		cliente.setBairro(infoEndereco.getBairro());
		cliente.setLocalidade(infoEndereco.getLocalidade());
		cliente.setUf(infoEndereco.getUf());
		cliente.setLogradouro(infoEndereco.getLogradouro());
		cliente.setEmail(cliente.getUsuario());
		if (cliente.getComplemento() == null) {
			cliente.setComplemento(infoEndereco.getComplemento());
		}
		cliente.setNumero(infoEndereco.getNumero());
	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(cliente.getSenha());
		cliente.setSenha(senhaEncoder);

		Pedido pedido = new Pedido();

		ListaDeDesejos listaDeDesejos = new ListaDeDesejos();

		clienteRepository.save(cliente);

		pedido.setCliente(cliente);

		listaDeDesejos.setCliente(cliente);

		pedidoRepository.save(pedido);
		listaDeDesejosRepository.save(listaDeDesejos);

		return Optional.of(clienteRepository.save(cliente));

	}
	
	

	public Optional<ClienteLogin> Logar(Optional <ClienteLogin> user){

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional <Cliente> cliente = clienteRepository.findByUsuario(user.get().getUsuario());

		if(cliente.isPresent()) {
			if(encoder.matches(user.get().getSenha(), cliente.get().getSenha())) {

				String auth = user.get().getUsuario()+":"+user.get().getSenha();
				byte[]encodedAuth=Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader="Basic " + new String (encodedAuth);

				user.get().setToken(authHeader);	
				user.get().setId(cliente.get().getId());
				user.get().setUf(cliente.get().getUf());
				user.get().setCpf(cliente.get().getCpf());
				user.get().setCep(cliente.get().getCep());
				user.get().setFoto(cliente.get().getFoto());
				user.get().setNome(cliente.get().getNome());
				user.get().setEmail(cliente.get().getEmail());
				user.get().setSenha(cliente.get().getSenha());
				user.get().setNumero(cliente.get().getNumero());
				user.get().setBairro(cliente.get().getBairro());
				user.get().setCelular(cliente.get().getCelular());
				user.get().setPedidos(cliente.get().getPedidos());
				user.get().setComplemento(cliente.get().getComplemento());
				user.get().setListaDeDesejos(cliente.get().getListaDeDesejos());
				
				return user;
			}
		}

		return null;
	}

}
