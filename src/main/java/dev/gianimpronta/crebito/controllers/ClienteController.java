package dev.gianimpronta.crebito.controllers;

import dev.gianimpronta.crebito.dto.*;
import dev.gianimpronta.crebito.entities.Cliente;
import dev.gianimpronta.crebito.entities.Transacoes;
import dev.gianimpronta.crebito.exception.*;
import dev.gianimpronta.crebito.repositories.ClienteRepository;
import dev.gianimpronta.crebito.repositories.TransacoesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class ClienteController {
    private final TransacoesRepository transacoesRepository;
    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository, TransacoesRepository transacoesRepository) {
        this.clienteRepository = clienteRepository;
        this.transacoesRepository = transacoesRepository;
    }

    @PostMapping("/clientes/{id}/transacoes")
    public TransacoesResponseDto transacao(@RequestBody TransacoesRequestDto request, @PathVariable Long id) {

        var cliente = clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
        if (request.tipo() == 'd') {
            var novoSaldo = cliente.getSaldo() - request.valor();
            if (novoSaldo < -cliente.getLimite()) {
                throw new SaldoInconsistenteException();
            }
            return getTransacoesResponseDto(request, cliente, novoSaldo);
        }
        if (request.tipo() == 'c') {
            long novoSaldo = cliente.getSaldo() + request.valor();
            return getTransacoesResponseDto(request, cliente, novoSaldo);
        }
        throw new TransacaoInvalidaException();
    }

    private TransacoesResponseDto getTransacoesResponseDto(@RequestBody TransacoesRequestDto request, Cliente cliente, long novoSaldo) {
        Transacoes transacao = new Transacoes();
        transacao.setValor(request.valor());
        transacao.setTipo(request.tipo());
        transacao.setDescricao(request.descricao());
        transacao.setRealizacao(LocalDateTime.now());
        transacao.setCliente(cliente);
        transacoesRepository.save(transacao);

        cliente.setSaldo(novoSaldo);
        clienteRepository.save(cliente);

        return new TransacoesResponseDto(cliente.getLimite(), cliente.getSaldo());
    }

    @GetMapping("/clientes/{id}/extrato")
    public ExtratoResponseDto extrato(@PathVariable Long id) {
        return clienteRepository.findById(id).map(cliente -> {
            var saldo = new ExtratoResponseDto.SaldoDto(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite());
            var transacoes = transacoesRepository.findFirst10ByCliente_IdOrderByRealizacaoDesc(id)
                    .stream()
                    .map(trans -> new ExtratoResponseDto.TransacoesDto(trans.getValor(), trans.getTipo(), trans.getDescricao(), trans.getRealizacao()))
                    .toList();
            return new ExtratoResponseDto(saldo, transacoes);
        }).orElseThrow(ClienteNotFoundException::new);
    }

}
