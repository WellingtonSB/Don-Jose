package br.com.wsb.DonJose.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wsb.DonJose.dto.CategoriaDTO;
import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.repository.CategoriaRepository;
import br.com.wsb.DonJose.service.CategoriaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	private CategoriaService service;

	@ApiOperation(value = "Busca por todas as categorias ")
	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategorias() {

		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "Busca por uma categoria especifica via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Busca por uma categoria especifica via nome")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@ApiOperation(value = "Cria uma nova categoria")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza uma categoria ")
	@PutMapping("/promocao")
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {
		return ResponseEntity.ok(repository.save(categoria));
	}

	@ApiOperation(value = "Exclui uma categoria")
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {

		repository.deleteById(id);
	}

}
