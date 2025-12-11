package com.eduardo.banco_crud.model;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass // Não se torna uma tabela,mas serve como base para outras entidades
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String nome;
    protected String email;
    protected String cpf;
    protected String senha;

}
