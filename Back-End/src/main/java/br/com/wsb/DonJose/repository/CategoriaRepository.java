package br.com.wsb.DonJose.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wsb.DonJose.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
}
