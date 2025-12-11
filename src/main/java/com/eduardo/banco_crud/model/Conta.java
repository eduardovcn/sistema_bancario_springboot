package com.eduardo.banco_crud.model;

import jakarta.persistence.*;
import lombok.*;
import com.eduardo.banco_crud.exception.InsufficientFundsException;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroConta;
    private BigDecimal saldo = BigDecimal.ZERO;


    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Conta(String numeroConta, Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
    }

    public void depositar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public void sacar(BigDecimal valor) {
        //Regra de négócio fundamental. Se aplica a entidade, não somente ao serviço.
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor de saque inválido");
        }
        if (saldo.compareTo(valor) < 0) {
            throw new InsufficientFundsException("Saldo insuficiente!");
        }
        saldo = saldo.subtract(valor);
    }

    public void transferir(BigDecimal valor, Conta conta) {
        this.sacar(valor);
        conta.depositar(valor);
    }

}
