package br.com.wsb.DonJose.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsb.DonJose.model.Cliente;
import br.com.wsb.DonJose.model.ClienteLogin;
import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.service.CepService;
import br.com.wsb.DonJose.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
    private CepService cepService;
	
	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAllByCliente() {

		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findByIdCliente(@PathVariable long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<ClienteLogin> Autentication(@RequestBody Optional<ClienteLogin> user) {
		return service.Logar(user).map(resp -> ResponseEntity.ok(resp))

				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Cliente> Post(@RequestBody Cliente usuario) {
		Optional<Cliente> user = service.CadastrarCliente(usuario);
		Cliente infoEndereco = cepService.buscaEnderecoPorCep(usuario.getCep());
		
		try {
			usuario.setBairro(infoEndereco.getBairro());
			usuario.setLocalidade(infoEndereco.getLocalidade());
			usuario.setUf(infoEndereco.getUf());
			usuario.setLogradouro(infoEndereco.getLogradouro());

			if (usuario.getComplemento() == null) {
				usuario.setComplemento(infoEndereco.getComplemento());
			}
			usuario.setNumero(usuario.getNumero());			
			return ResponseEntity.ok(user.get());

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();

		}
	}

	@PutMapping
	public ResponseEntity<Cliente> putCliente(@RequestBody Cliente cliente) {

		return ResponseEntity.ok(repository.save(cliente));
	}

	@DeleteMapping("/{id}")
	public void deletaCliente(@PathVariable long id) {

		repository.deleteById(id);
	}

}
