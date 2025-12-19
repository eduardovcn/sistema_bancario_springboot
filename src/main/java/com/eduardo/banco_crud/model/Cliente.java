package com.eduardo.banco_crud.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Usuario {
    // Referir o nome do atributo na classe Conta que mapeia o relacionamento, ou seja, "cliente".
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Conta> contas;



    }

