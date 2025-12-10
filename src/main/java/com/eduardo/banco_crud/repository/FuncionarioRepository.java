package com.eduardo.banco_crud.repository;

import com.eduardo.banco_crud.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
