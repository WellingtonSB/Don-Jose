package br.com.wsb.DonJose.service;

import java.time.*;
import java.util.*;

import br.com.wsb.DonJose.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.repository.*;
import br.com.wsb.DonJose.service.exceptions.DataIntegrityException;
import br.com.wsb.DonJose.service.exceptions.FileException;
import br.com.wsb.DonJose.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto cadastrarProduto(Produto produto) {
		List<Produto> produtos = produtoRepository.findAll();
		int last = produtos.size() - 1;

		if (produtos.size() != 0) {
			produto.setPlu(produtos.get(last).getPlu() + 1);

		} else {
			produto.setPlu(1);
		}

		return produtoRepository.save(produto);
	}
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String nome, Iterable<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll();
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}
	
	public Produto stock(Integer idProduto) {
		Optional<Produto>produtoExistente = produtoRepository.findById(idProduto);
		if (produtoExistente.isPresent() && produtoExistente.get().getEstoque() == 0) {
			throw new FileException("Estoque vazio!");
		}
		produtoExistente.get().setEstoque(produtoExistente.get().getEstoque()-produtoExistente.get().getQtdProd());
		return produtoRepository.save(produtoExistente.get());
	}
	
}
