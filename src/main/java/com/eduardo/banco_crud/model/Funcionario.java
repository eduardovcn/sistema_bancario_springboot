package com.eduardo.banco_crud.model;

import com.eduardo.banco_crud.repository.ClienteRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.id.uuid.UuidGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Usuario {

    private String cargo;

    public void criarContaCliente(Cliente cliente) {


    }

    public void encerrarContaCliente(Cliente cliente) {

    }
}
