package dev.gianimpronta.crebito.dto;

import java.io.Serializable;

/**
 * DTO for {@link dev.gianimpronta.crebito.entities.Transacoes}
 */
public record TransacoesRequestDto(Long valor, char tipo, String descricao) implements Serializable {
}