package com.eduardo.banco_crud.controller;


import com.eduardo.banco_crud.dto.ClienteRequestDTO;
import com.eduardo.banco_crud.dto.ClienteResponseDTO;
import com.eduardo.banco_crud.model.Cliente;
import com.eduardo.banco_crud.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/area_cliente")
public class ClienteController {

    private final ClienteService clienteService;


    @PostMapping("/novo_cliente")
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody @Valid ClienteRequestDTO cliente) {

        Cliente clienteSalvo = clienteService.criarCliente(cliente);

        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(clienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED) // Cria o status HTTP 201
                .body(clienteResponseDTO); // Faz o objeto criado ser retornado no corpo da resposta.
    }

    @DeleteMapping("/deletar_cliente/{id}") // {id} indica um valor dinâmico na URL.
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        // @PathVariable indica que o valor do id virá da URL.
        clienteService.deletarCliente(id);

        // Deve retornar 204 No Content quando a deleção for bem-sucedida.
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar_por_id/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente); // Retorna 200 OK com o cliente no corpo da resposta.
    }

    @GetMapping("/buscar_por_cpf")
    public ResponseEntity<ClienteResponseDTO> buscarClientePorCpf(@RequestBody ClienteRequestDTO cpfBusca) {

        // 1. Chama o serviço passando o CPF que veio dentro do DTO
        Optional<Cliente> clienteOpt = clienteService.buscarClientePorCpf(cpfBusca.cpf());

        // 2. Se o cliente existir, converte para DTO de resposta e devolve 200
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            return ResponseEntity.ok(new ClienteResponseDTO(cliente));
        }

        // 3. Se não achar, devolve 404
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes); // Retorna 200 OK com a lista de clientes no corpo da resposta.
    }


}
