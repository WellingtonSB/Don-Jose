package br.com.wsb.DonJose.controller;

import java.util.List;

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

import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.repository.CategoriaRepository;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@ApiOperation(value = "Busca por todas as categorias ")
	@GetMapping
	public ResponseEntity<List<Categoria>> findAllCategorias() {
			
		return ResponseEntity.ok(repository.findAll());
	}
	
	@ApiOperation(value = "Busca por uma categoria especifica via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findByIdCategoria(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Busca por uma categoria especifica via nome")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@ApiOperation(value = "Cria uma nova categoria")
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@ApiOperation(value = "Atualiza uma categoria ")
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {
		
		return ResponseEntity.ok(repository.save(categoria));
	}
	
	@ApiOperation(value = "Exclui uma categoria")
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable long id) {
		
		repository.deleteById(id);
	}

}
