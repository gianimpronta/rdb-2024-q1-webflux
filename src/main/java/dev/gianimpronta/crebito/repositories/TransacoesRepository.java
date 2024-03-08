package dev.gianimpronta.crebito.repositories;

import dev.gianimpronta.crebito.entities.Transacoes;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransacoesRepository extends ReactiveCrudRepository<Transacoes, Long> {
    Flux<Transacoes> findFirst10ByCliente_IdOrderByRealizacaoDesc(Long id);

}