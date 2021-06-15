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
@RequestMapping("/listadesejo")
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
	public ResponseEntity<Carrinho> findByIdListaDeDesejos(@PathVariable long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/listaDeDesejo/{idListaDeDesejo}/nome/{nome}")
	public ResponseEntity<List<Produto>> findAllByNomeProdutoListaDeDesejos(@PathVariable long idListaDeDesejo,
			@PathVariable String nome) {

		return ResponseEntity.ok(service.pesquisaPorIdDeProdutoNaListaDeDesejos(idListaDeDesejo, nome));
	}

	@GetMapping("/listaDeDesejo/{idListaDeDesejo}")
	public ResponseEntity<List<Produto>> findAllByProdutosListaDeDesejos(@PathVariable long idListaDeDesejo) {
		return ResponseEntity.ok(service.pesquisaPorProdutoNaListaDeDesejos(idListaDeDesejo));
	}

	@PostMapping
	public ResponseEntity<Carrinho> postListaDeDesejos(@RequestBody Carrinho listaDeDesejos) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(listaDeDesejos));
	}

	@PutMapping
	public ResponseEntity<Carrinho> putListaDeDesejos(@RequestBody Carrinho listaDeDesejos) {

		return ResponseEntity.ok(repository.save(listaDeDesejos));
	}

	@DeleteMapping("/produto_lista/produtos/{idProduto}/listaDesejos/{idListaDeDesejo}")
	public ResponseEntity<Produto> removeProdutoListaDeDesejos(@PathVariable long idProduto,
			@PathVariable long idListaDeDesejo) {

		return ResponseEntity.ok(service.removeProdutoListaDeDesejo(idProduto, idListaDeDesejo));
	}

	@DeleteMapping("/{id}")
	public void deleteListaDeDesejos(@PathVariable long id) {

		repository.deleteById(id);
	}

}
