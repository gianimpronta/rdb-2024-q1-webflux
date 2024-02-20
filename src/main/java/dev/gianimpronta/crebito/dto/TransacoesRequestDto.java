package dev.gianimpronta.crebito.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link dev.gianimpronta.crebito.entities.Transacoes}
 */
public record TransacoesRequestDto(
        @PositiveOrZero
        @Digits(fraction = 0, integer = 20)
        BigDecimal valor,
        @NotNull
        @Pattern(regexp = "[cd]")
        String tipo,
        @NotBlank
        @NotNull
        @Pattern(regexp = "\\w{1,10}")
        String descricao) implements Serializable {
}