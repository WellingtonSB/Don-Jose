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

import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.model.Produto;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.service.ProdutoService;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProdutoService service;
	
	@ApiOperation(value = "Busca todos os pedidos")
	@GetMapping
	public ResponseEntity<List<Pedido>> findAllByPedidos() {
		
		return ResponseEntity.ok(repository.findAll());
	}
	
	@ApiOperation(value = "Busca por um pedido especifico via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findByIdPedido(@PathVariable long id) {
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Busca por um pedido especifico via numero do pedido")
	@GetMapping("/numeroPedido/{numeroPedido}")
	public ResponseEntity<List<Pedido>>FindByNumeroPedido(@PathVariable int numeroPedido){
		return ResponseEntity.ok(repository.findByNumeroPedido(numeroPedido));
	}
	
	@ApiOperation(value = "Busca por produtos contidos dentro do carrinho")
	@GetMapping("/meuspedidos/{idPedido}")
	public ResponseEntity<List<Produto>> findAllByProdutosCarrinho(@PathVariable long idPedido) {
		
		return ResponseEntity.ok(service.pesquisaPorProdutoNoCarrinho(idPedido));
	}
	
	@ApiOperation(value = "Cria um novo pedido")
	@PostMapping
	public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pedido));
	}
	
	
	@ApiOperation(value = "Deleta o pedido")
	@DeleteMapping("/{id}")
	public void deletePedido(@PathVariable long id) {
		
		repository.deleteById(id);
	}
	
	@ApiOperation(value = "Demove um produto especifico do pedido")
	@DeleteMapping("/produto_pedido/produtos/{idProduto}/pedidos/{idPedido}")
	public void putProduto(@PathVariable long idProduto, @PathVariable long idPedido) {
		
		service.deletarProduto(idProduto, idPedido);
	}

}
