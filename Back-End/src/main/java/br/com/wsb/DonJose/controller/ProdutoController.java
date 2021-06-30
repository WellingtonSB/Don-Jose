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

import br.com.wsb.DonJose.model.Produto;
import br.com.wsb.DonJose.repository.ProdutoRepository;
import br.com.wsb.DonJose.service.ProdutoService;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ProdutoService service;

	@ApiOperation(value = "Busca por todos os produtos")
	@GetMapping
	public ResponseEntity<List<Produto>> findAllByProdutos() {

		return ResponseEntity.ok(repository.findAll());
	}

	@ApiOperation(value = "Busca por um produto especifico via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findByIdProduto(@PathVariable long id) {

		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Busca por o produto com o maior preco ")
	@GetMapping("/maiorPreco/{preco}")
	public ResponseEntity<List<Produto>> GetAllByPrecoMaior(@PathVariable double preco) {
		return ResponseEntity.ok(repository.findAllByPrecoGreaterThanEqual(preco));
	}

	@ApiOperation(value = "Busca por o produto com menor preco")
	@GetMapping("/menorPreco/{preco}")
	public ResponseEntity<List<Produto>> GetAllByPrecoLess(@PathVariable double preco) {
		return ResponseEntity.ok(repository.findAllByPrecoLessThanEqual(preco));
	}
	
	@ApiOperation(value = "Busca por um produto especifico via nome ")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> findAllByNomeProdutos(@PathVariable String nome) {

		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@ApiOperation(value = "Cria um novo produto")
	@PostMapping
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarProduto(produto));
	}

	@ApiOperation(value = "Atualiza um produto especifico")
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto) {

		return ResponseEntity.ok(repository.save(produto));
	}

	@ApiOperation(value = "Adiciona o produto no carrinho(compra diretamente)")
	@PutMapping("/produto_pedido/produtos/{idProduto}/pedidos/{idPedido}")
	public ResponseEntity<Produto> comprarProduto(@PathVariable long idProduto,
			@PathVariable long idPedido) {

		return ResponseEntity.ok(service.compraProduto(idProduto, idPedido));
	}
	
	//ajustar logica, criar uma lista de desejos
	@ApiOperation(value = "Adiciona o produto no carrinho(necessita de autorizacao por parte do cliente para processeguir com a compra)")
	@PutMapping("/produto_lista/produtos/{idProduto}/carrinho/{idCarrinho}")
	public ResponseEntity<Produto> adicionarProdutonoCarrinho(@PathVariable long idProduto,
			@PathVariable long idCarrinho) {

		return ResponseEntity.ok(service.adicionarProdutoNoCarrinho(idProduto, idCarrinho));
	}
	
	
	@ApiOperation(value = "Deleta um produto")
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {

		repository.deleteById(id);
	}

}
