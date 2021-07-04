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

import br.com.wsb.DonJose.model.ListaDeDesejos;
import br.com.wsb.DonJose.model.Produto;
import br.com.wsb.DonJose.repository.ListaDeDesejosRepository;
import br.com.wsb.DonJose.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/listaDeDesejo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ListaDeDesejosController {

	@Autowired
	private ListaDeDesejosRepository repository;

	@Autowired
	private ProdutoService service;

	@ApiOperation(value = "Procura por todos as listas de desejo")
	@GetMapping 
	public ResponseEntity<List<ListaDeDesejos>> findAllByCarrinho() {

		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "Procura por um  listas de desejo específico via ID ")
	@GetMapping("/{id}")
	public ResponseEntity<ListaDeDesejos> findByIdListaDeDesejos(@PathVariable long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "Procura por uma  listas de desejo específico via nome ")
	@GetMapping("/listaDeDesejo/{idListaDeDesejo}/nome/{nome}")
	public ResponseEntity<List<Produto>> findAllByNomeProdutoListaDeDesejos(@PathVariable long idListaDeDesejo, @PathVariable String nome) {
		
		return ResponseEntity.ok(service.pesquisaPorIdDeProdutoNaListaDeDesejos(idListaDeDesejo, nome));
	}
	
	@ApiOperation(value = "Procura por produtos contidos em uma  listas de desejo via ID ")
	@GetMapping("/listaDeDesejo/{idListaDeDesejo}")
	public ResponseEntity<List<Produto>> findAllByProdutosCarrinho(@PathVariable long idListaDeDesejo) {
		return ResponseEntity.ok(service.pesquisaPorProdutoNaListaDeDesejos(idListaDeDesejo));
	}

	@ApiOperation(value = "Cria uma  listas de desejo ")
	@PostMapping
	public ResponseEntity<ListaDeDesejos> post(@RequestBody ListaDeDesejos carrinho) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carrinho));
	}

	@ApiOperation(value = "Atualiza a listas de desejo ")
	@PutMapping
	public ResponseEntity<ListaDeDesejos> putListaDeDesejos(@RequestBody ListaDeDesejos listaDeDesejos) {
		return ResponseEntity.ok(repository.save(listaDeDesejos));
	}

	@DeleteMapping("/produto_lista/produtos/{idProduto}/listaDesejos/{idListaDeDesejo}")
	public ResponseEntity<Produto> removeProdutoListaDeDesejos(@PathVariable long idProduto, @PathVariable long idListaDeDesejo) {
		return ResponseEntity.ok(service.removeProdutoListaDeDesejo(idProduto, idListaDeDesejo));
	}

	@ApiOperation(value = "Remove a produto/ listas de desejo ")
	@DeleteMapping("/{id}")
	public void deletarProduto(@PathVariable long id) {
		repository.deleteById(id);
	}

}
