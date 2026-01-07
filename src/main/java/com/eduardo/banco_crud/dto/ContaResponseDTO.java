package com.eduardo.banco_crud.dto;

import com.eduardo.banco_crud.model.Conta;
import java.math.BigDecimal;


public record ContaResponseDTO(Long id, String numeroConta, BigDecimal saldo) {

    public ContaResponseDTO(Conta conta) {
        this(conta.getId(), conta.getNumeroConta(), conta.getSaldo());
    }



}

