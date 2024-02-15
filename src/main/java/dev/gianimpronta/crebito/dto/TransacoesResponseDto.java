package dev.gianimpronta.crebito.dto;

import java.io.Serializable;

/**
 * DTO for {@link dev.gianimpronta.crebito.entities.Cliente}
 */
public record TransacoesResponseDto(Long limite, Long saldo) implements Serializable {
}