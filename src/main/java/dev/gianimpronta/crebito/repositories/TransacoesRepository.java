package dev.gianimpronta.crebito.repositories;

import dev.gianimpronta.crebito.entities.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
    List<Transacoes> findFirst10ByCliente_IdOrderByRealizacaoDesc(Long id);

}