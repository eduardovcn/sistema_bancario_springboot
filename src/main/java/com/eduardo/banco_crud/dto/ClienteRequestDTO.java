package com.eduardo.banco_crud.dto;

import com.eduardo.banco_crud.model.Cliente;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDTO(@NotBlank String nome, @NotBlank String cpf, @NotBlank String email, @NotBlank String senha) {

    public Cliente toEntity() {
        Cliente cliente =  new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setEmail(this.email);
        cliente.setSenha(this.senha);
        return cliente;
    }

}
