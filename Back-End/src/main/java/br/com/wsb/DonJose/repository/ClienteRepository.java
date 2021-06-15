package br.com.wsb.DonJose.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsb.DonJose.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByEmail(String email);
}
