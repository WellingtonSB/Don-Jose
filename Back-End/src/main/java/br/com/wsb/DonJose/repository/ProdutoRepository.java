package br.com.wsb.DonJose.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wsb.DonJose.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	public List<Produto> findAllByPrecoLessThanEqual(double preco);
	public List<Produto> findAllByPrecoGreaterThanEqual(double preco);
}
