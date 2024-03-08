package dev.gianimpronta.crebito.controllers;

import dev.gianimpronta.crebito.dto.*;
import dev.gianimpronta.crebito.entities.Transacoes;
import dev.gianimpronta.crebito.exception.*;
import dev.gianimpronta.crebito.repositories.ClienteRepository;
import dev.gianimpronta.crebito.repositories.TransacoesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ClienteController {
    private final TransacoesRepository transacoesRepository;
    private final ClienteRepository clienteRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @PostMapping("/clientes/{id}/transacoes")
    public Mono<ResponseEntity<TransacoesResponseDto>> transacao(
            @RequestBody @Valid TransacoesRequestDto request,
            @PathVariable Long id
    ) {
        if (id < 1 || id > 5) {
            throw new ClienteNotFoundException();
        }
        Long valor = Long.parseLong(request.valor().toString());
        var transPublisher = transacoesRepository
                .save(Transacoes.builder()
                        .cliente(id)
                        .descricao(request.descricao())
                        .tipo(request.tipo())
                        .realizacao(LocalDateTime.now())
                        .valor(request.valor().longValue())
                        .build()
                );
        return clienteRepository
                .findById(id)
                .flatMap(cliente -> {
                            if (request.tipo().equals("d")) {
                                var novoSaldo = cliente.getSaldo() - valor;
                                if (novoSaldo < -cliente.getLimite()) {
                                    return Mono.error(SaldoInconsistenteException::new);
                                }
                                cliente.setSaldo(novoSaldo);
                            } else if (request.tipo().equals("c")) {
                                cliente.setSaldo(cliente.getSaldo() + valor);
                            } else {
                                return Mono.error(TransacaoInvalidaException::new);
                            }

                            return Mono.just(cliente);
                        }
                )
                .flatMap(clienteRepository::save)
                .flatMap(transPublisher::thenReturn)
                .flatMap(cliente -> Mono.just(ResponseEntity.ok(
                        new TransacoesResponseDto(cliente.getLimite(), cliente.getSaldo())
                )))
                .onErrorResume(throwable -> Mono.just(ResponseEntity.unprocessableEntity().build()));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @GetMapping("/clientes/{id}/extrato")
    public Mono<ExtratoResponseDto> extrato(@PathVariable Long id) {
        return clienteRepository
                .findById(id)
                .cache()
                .subscribeOn(Schedulers.parallel())
                .map(cliente -> {
                    var saldo = new ExtratoResponseDto.SaldoDto(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite());
                    var transacoes = transacoesRepository.findFirst10ByCliente_IdOrderByRealizacaoDesc(id)
                            .cache()
                            .subscribeOn(Schedulers.parallel())
                            .map(trans -> new ExtratoResponseDto.TransacoesDto(trans.getValor(), trans.getTipo(), trans.getDescricao(), trans.getRealizacao()))
                            .collectList();
                    return Mono.just(new ExtratoResponseDto(saldo, transacoes));
                }).orElseThrow(ClienteNotFoundException::new);
    }
}
