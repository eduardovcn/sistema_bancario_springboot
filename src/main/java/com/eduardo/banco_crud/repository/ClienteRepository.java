package com.eduardo.banco_crud.repository;

import com.eduardo.banco_crud.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
