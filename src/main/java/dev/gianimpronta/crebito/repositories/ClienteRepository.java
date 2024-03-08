package dev.gianimpronta.crebito.repositories;

import dev.gianimpronta.crebito.entities.Cliente;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Long> {
}