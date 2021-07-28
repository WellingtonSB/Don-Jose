package br.com.wsb.DonJose.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wsb.DonJose.repository.ClienteRepository;
import br.com.wsb.DonJose.service.AuthService;
import br.com.wsb.DonJose.service.CepService;
import br.com.wsb.DonJose.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import br.com.wsb.DonJose.dto.CategoriaDTO;
import br.com.wsb.DonJose.dto.ClienteDTO;
import br.com.wsb.DonJose.dto.ClienteNewDTO;
import br.com.wsb.DonJose.dto.EmailDTO;
import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.model.Cliente;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteService service;
	
	@Autowired
	private CepService cepService;
	
	@Autowired 
	public AuthService authService;

	@ApiOperation(value = "Busca por todos os clientes")
	@GetMapping
	public ResponseEntity<List<Cliente>> findAllByCliente() {

		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "Busca por um cliente especifico via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findByIdCliente(@PathVariable Integer id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	//http://localhost:8080/categorias/page?linesPerPage=9&page=1%&direction=DESC 
		@ApiOperation(value = "Responsavel pela paginacao")
		@GetMapping("/page")
		public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
			Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
			return ResponseEntity.ok().body(listDto);
		}
	
	
	
	/*@ApiOperation(value = "Loga em uma conta existente")
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
	public void deletaCliente(@PathVariable Integer id) {

		repository.deleteById(id);
	}
	
	@PostMapping("/esqueciasenha")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) throws ObjectNotFoundException {
		authService.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
	
		@ApiOperation(value = "Cria uma nova conta")
		@PostMapping("/cadastrar")
		public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
			Cliente obj = service.fromDTO(objDto);
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}*/
}
