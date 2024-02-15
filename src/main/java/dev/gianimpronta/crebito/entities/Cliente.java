package dev.gianimpronta.crebito.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "limite", nullable = false)
    private Long limite;

    @Column(name = "saldo")
    private Long saldo;

    public Cliente(Long id, Long limite, Long saldo) {
        this.id = id;
        this.limite = limite;
        this.saldo = saldo;
    }

    @OneToMany(mappedBy = "cliente", orphanRemoval = true)
    private List<Transacoes> transacoes = new ArrayList<>();

}