package com.eduardo.banco_crud.service;

import com.eduardo.banco_crud.dto.ClienteRequestDTO;
import com.eduardo.banco_crud.dto.ClienteResponseDTO;
import com.eduardo.banco_crud.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.eduardo.banco_crud.repository.ClienteRepository;
import com.eduardo.banco_crud.model.Cliente;


import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContaRepository contaRepository;


    @Transactional
    public Cliente criarCliente(ClienteRequestDTO cliente) {

        String cpf = cliente.cpf().trim();

        if (clienteRepository.existsByCpf(cpf)) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF.");
        }

        Cliente novoCliente = cliente.toEntity();
        novoCliente.setCpf(cpf);

        return clienteRepository.save(novoCliente);
    }

    @Transactional
    public Optional <Cliente> buscarClientePorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id do cliente é obrigatório");
        }

        return clienteRepository.findById(id);
    }

    @Transactional
    public Optional <Cliente> buscarClientePorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Optional<Cliente> clienteBuscado = clienteRepository.findByCpf(cpf.trim());

        return clienteBuscado;

    }
    
    @Transactional
    public List<Cliente> listarClientes() {
        
        return clienteRepository.findAll();
    }

    @Transactional
    public void deletarCliente(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id do cliente é obrigatório");
        }
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        if (contaRepository.existsByClienteId(id)) {
            throw new IllegalStateException("Não é possível deletar o cliente, pois ele possui contas associadas.");
        }
        try {
            clienteRepository.deleteById(id);

        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Não foi possível deletar o cliente: Ainda existem contas associadas a ele.", ex);
        }
    }


}

