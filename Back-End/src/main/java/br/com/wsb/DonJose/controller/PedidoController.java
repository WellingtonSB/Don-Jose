package br.com.wsb.DonJose.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import br.com.wsb.DonJose.dto.CategoriaDTO;
import br.com.wsb.DonJose.model.Categoria;
import br.com.wsb.DonJose.model.Pedido;
import br.com.wsb.DonJose.model.Produto;
import br.com.wsb.DonJose.repository.PedidoRepository;
import br.com.wsb.DonJose.service.PedidoService;
import br.com.wsb.DonJose.service.ProdutoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@ApiOperation(value = "Busca por um pedido especifico via ID")
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Cria uma nova categoria")
	@PostMapping("/criar")
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/*
	 * @GetMapping public ResponseEntity<Page<Pedido>> findPage(
	 * 
	 * @RequestParam(value="page", defaultValue="0") Integer page,
	 * 
	 * @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
	 * 
	 * @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
	 * 
	 * @RequestParam(value="direction", defaultValue="DESC") String direction) {
	 * Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
	 * return ResponseEntity.ok().body(list); }
	 */
}
