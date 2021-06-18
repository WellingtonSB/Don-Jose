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

import br.com.wsb.DonJose.model.Carrinho;
import br.com.wsb.DonJose.model.Produto;
import br.com.wsb.DonJose.repository.CarrinhoRepository;
import br.com.wsb.DonJose.service.ProdutoService;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarrinhoController {

	@Autowired
	private CarrinhoRepository repository;

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Carrinho>> findAllByListaDeDesejos() {

		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Carrinho> findById(@PathVariable long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/carrinho/{idCarrinho}/nome/{nome}")
	public ResponseEntity<List<Produto>> findAllByNomeProdutoCarrinho(@PathVariable long idCarrinho,
			@PathVariable String nome) {

		return ResponseEntity.ok(service.pesquisaPorIdDeProdutoCarrinho(idCarrinho, nome));
	}

	@GetMapping("/carrinho/{idCarrinho}")
	public ResponseEntity<List<Produto>> findAllByProdutosCarrinho(@PathVariable long idCarrinho) {
		return ResponseEntity.ok(service.pesquisaPorProdutoNoCarrinho(idCarrinho));
	}

	@PostMapping
	public ResponseEntity<Carrinho> post(@RequestBody Carrinho carrinho) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(carrinho));
	}

	@PutMapping
	public ResponseEntity<Carrinho> putListaDeDesejos(@RequestBody Carrinho carrinho) {

		return ResponseEntity.ok(repository.save(carrinho));
	}

	@DeleteMapping("/produto_lista/produtos/{idProduto}/carrinho/{idCarrinho}")
	public ResponseEntity<Produto> removeProdutoListaDeDesejos(@PathVariable long idProduto,
			@PathVariable long idCarrinho) {

		return ResponseEntity.ok(service.removeProdutoListaDeDesejo(idProduto, idCarrinho));
	}

	@DeleteMapping("/{id}")
	public void deletarProduto(@PathVariable long id) {

		repository.deleteById(id);
	}

}
