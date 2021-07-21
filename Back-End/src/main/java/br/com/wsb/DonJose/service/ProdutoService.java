package br.com.wsb.DonJose.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsb.DonJose.model.*;

import br.com.wsb.DonJose.repository.*;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
}
