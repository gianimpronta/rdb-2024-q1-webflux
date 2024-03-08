package dev.gianimpronta.crebito.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.gianimpronta.crebito.entities.Transacoes;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ExtratoResponseDto(
        SaldoDto saldo,
        @JsonProperty("ultimas_transacoes")
        List<Transacoes> ultimasTransacoes
) implements Serializable {

    @Builder
    public record SaldoDto(Long total, LocalDateTime data_extrato, Long limite) implements Serializable {
    }
}