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

    @OneToMany(mappedBy = "id_cliente", fetch = FetchType.LAZY)
    private List<Conta> contas;



    }

