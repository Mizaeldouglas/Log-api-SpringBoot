package io.github.mizaeldouglas.logapi.domain.repository;

import io.github.mizaeldouglas.logapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);

	Optional<Cliente> findByEmail(String email);
}
