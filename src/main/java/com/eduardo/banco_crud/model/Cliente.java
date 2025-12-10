package com.eduardo.banco_crud.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario {

    private BigDecimal saldo = BigDecimal.ZERO; // Saldo inicial zero

    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    public void sacar(BigDecimal valor){
        if (saldo.compareTo(valor) >= 0) {
            saldo = saldo.subtract(valor);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }
    }
}
