package dev.gianimpronta.crebito.dto;

import dev.gianimpronta.crebito.entities.Cliente;
import dev.gianimpronta.crebito.entities.Transacoes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Cliente}
 */
public record ExtratoResponseDto(SaldoDto saldo, List<TransacoesDto> ultimas_transacoes) implements Serializable {
    /**
     * DTO for {@link Transacoes}
     */
    public record TransacoesDto(Long valor, String tipo, String descricao, LocalDateTime realizada_em) implements Serializable {
    }

    public record SaldoDto(Long total, LocalDateTime data_extrato, Long limite) implements Serializable {
    }
}