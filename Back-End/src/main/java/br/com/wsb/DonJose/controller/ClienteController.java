package br.com.wsb.DonJose.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteService service;
	
	@Autowired
	private CepService cepService;
	

	@ApiOperation(value = "Busca por todos os clientes")
	@GetMapping
	public ResponseEntity<List<Cliente>> findAllByCliente() {

		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "Busca por um cliente especifico via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findByIdCliente(@PathVariable long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "Loga em uma conta existente")
	@PostMapping("/logar")
	public ResponseEntity<ClienteLogin> Autentication(@RequestBody Optional<ClienteLogin> user) {
		return service.Logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@ApiOperation(value = "Cria uma nova conta")
	@PostMapping("/cadastrar")
	public ResponseEntity<Cliente> Post(@RequestBody Cliente usuario) {
		Optional<Cliente> user = service.CadastrarCliente(usuario);

		try {
			return ResponseEntity.ok(user.get());

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();

		}
	}

	@ApiOperation(value = "Atualiza os dados da conta")
	@PutMapping("/atualizar")
	public ResponseEntity<Cliente> Put(@RequestBody Cliente cliente) {
		Optional<Cliente> user = service.CadastrarCliente(cliente);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(user.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@ApiOperation(value = "Deleta a conta")
	@DeleteMapping("/{id}")
	public void deletaCliente(@PathVariable long id) {

		repository.deleteById(id);
	}

	//viaCEP	
	
}
	

