package dev.gianimpronta.crebito.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link dev.gianimpronta.crebito.entities.Transacoes}
 */
public record TransacoesRequestDto(
        @PositiveOrZero
        @NotNull
        @Min(0)
        Integer valor,
        @NotNull
        @NotEmpty
        @Pattern(regexp = "[cd]")
        String tipo,
        @NotBlank
        @NotNull
        @NotEmpty
        @Length(max = 10)
        String descricao) implements Serializable {
}