package dev.gianimpronta.crebito.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transacoes")
public class Transacoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "valor", nullable = false)
    private Long valor;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "descricao", nullable = false, length = 10)
    private String descricao;

    @Column(name = "realizacao", nullable = false)
    private LocalDateTime realizacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}