package io.github.mizaeldouglas.logapi.domain.repository;

import io.github.mizaeldouglas.logapi.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
