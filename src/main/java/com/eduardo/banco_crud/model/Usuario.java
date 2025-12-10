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

    private String nome;
    private String email;
    private String cpf;
    private String senha;
}
