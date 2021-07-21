package br.com.wsb.DonJose.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
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

	/*@Autowired
	private ProdutoRepository repository;

	@Autowired
	private ProdutoService service;

	@ApiOperation(value = "Busca por todos os produtos")
	@GetMapping
	public ResponseEntity<List<Produto>> findAllByProdutos() {

		return ResponseEntity.ok(repository.findAll());
	}

	/*
	 														(Paginação)
	  
	  @ApiOperation(value = "Busca por todos os produtos usando paginacao")
	@GetMapping("/prods")
	public ResponseEntity<Page<Produto>>getAllProdutos(@PageableDefault(page=0 , size=5, sort ="id",direction = Sort.Direction.ASC)Pageable pageable){
		Page<Produto> produtosList = repository.findAll(pageable);
		if(produtosList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			for(Produto produto :produtosList) {
				long id = produto.getId();
				produto.add(linkTo(methodOn(ProdutoController.class).getOneProduto(id)).withSelfRel());
			}
			return new ResponseEntity<>(produtosList,HttpStatus.OK);
		}
	}


	@GetMapping("/prod/{id}")
	public ResponseEntity<Produto>getOneProduto(@PathVariable(value="id")long id){
		Optional<Produto>produto0 = repository.findById(id);
		if(!produto0.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Object pageable;
			produto0.get().add(linkTo(methodOn(ProdutoController.class).getAllProdutos(pageable:null)).withRel("Lista de Produtos"));
			return new ResponseEntity<Produto>(produto0.get(),HttpStatus.OK);
		}
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

	@ApiOperation(value = "Atualiza um produto")
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto) {	
		return ResponseEntity.ok(repository.save(produto));
	}

	@ApiOperation(value = "Adiciona o produto no carrinho (compra diretamente)")
	@PutMapping("/produto_pedido/produtos/{idProduto}/pedidos/{idPedido}")
	public ResponseEntity<Produto> putProduto(@PathVariable long idProduto, @PathVariable long idPedido) {
		
		return ResponseEntity.ok(service.compraProduto(idProduto, idPedido));
	}
	
	@ApiOperation(value = "Adiciona o produto na lista de desejos (compra diretamente)")
	@PutMapping("/produto_lista/produtos/{idProduto}/listaDesejos/{idListaDeDesejo}")
	public ResponseEntity<Produto> adicionaProdutoListaDeDesejos(@PathVariable long idProduto, @PathVariable long idListaDeDesejo) {		
		return ResponseEntity.ok(service.adicionarProdutoListaDeDesejo(idProduto, idListaDeDesejo));
	}
	
	@ApiOperation(value = "Deleta um produto")
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		
		repository.deleteById(id);
	}*/

}
