package com.eduardo.banco_crud.repository;

import com.eduardo.banco_crud.model.Cliente;
import com.eduardo.banco_crud.model.Conta;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByClienteId(Long clienteId);

    Optional<Conta> findByNumeroConta(String numeroConta);

    boolean existsByClienteId(Long clienteId);

}
