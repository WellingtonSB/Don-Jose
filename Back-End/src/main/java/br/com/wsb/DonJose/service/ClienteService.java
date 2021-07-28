package br.com.wsb.DonJose.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.ListaDeDesejos;
import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.model.enums.Perfil;
import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.repository.ListaDeDesejosRepository;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.security.UserSS;
import br.com.wsb.DonJose.service.exceptions.AuthorizationException;
import br.com.wsb.DonJose.service.exceptions.DataIntegrityException;
import br.com.wsb.DonJose.service.exceptions.ObjectNotFoundException;
import br.com.wsb.DonJose.dto.ClienteDTO;
import br.com.wsb.DonJose.dto.ClienteNewDTO;
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
	private BCryptPasswordEncoder pe;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
	
		Cliente obj = clienteRepository.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		return obj;
	}
	
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),null, null, null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), pe.encode(objDto.getSenha()), null, null, null);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
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
