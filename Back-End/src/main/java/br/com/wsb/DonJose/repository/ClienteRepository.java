package br.com.wsb.DonJose.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.wsb.DonJose.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public List<Cliente> findAllByNomeContainingIgnoreCase(String nome);

	public Optional<Cliente> findByUsuario(String userName);

	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
