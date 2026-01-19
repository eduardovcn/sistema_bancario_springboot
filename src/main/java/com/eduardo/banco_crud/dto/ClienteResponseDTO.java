package com.eduardo.banco_crud.dto;

import com.eduardo.banco_crud.model.Cliente;

public record ClienteResponseDTO(Long id, String nome, String cpf){

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf());
    }

}
