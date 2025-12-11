package com.eduardo.banco_crud.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.eduardo.banco_crud.repository.ClienteRepository;
import com.eduardo.banco_crud.model.Cliente;


import java.util.Objects;


@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente criarCliente(Cliente cliente) {
        //Lógica para chegar se o CPF é válido e se não há outro cliente com o mesmo CPF.
        Objects.requireNonNull(cliente, "Cliente não pode ser nulo");
        String cpf = cliente.getCpf() != null ? cliente.getCpf().trim() : null;
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        if (clienteRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF.");
        }
        cliente.setCpf(cpf);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente buscarClientePorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id do cliente é obrigatório");
        }
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com este CPF"));
    }


    @Transactional
    public void deletarCliente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id do cliente é obrigatório");
        }
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }


}

