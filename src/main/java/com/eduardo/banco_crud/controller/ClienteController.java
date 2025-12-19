package com.eduardo.banco_crud.controller;


import com.eduardo.banco_crud.model.Cliente;
import com.eduardo.banco_crud.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/home")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/ola")
    public String ola(String nome) {

        nome = "Eduardo";

        return clienteService.ola(nome);
    }

    @PostMapping("/criar_cliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {

        Cliente novoCliente = clienteService.criarCliente(cliente);



        return ResponseEntity.status(HttpStatus.CREATED) // Cria o status HTTP 201
                .header("X-SISTEMA-BANCARIO", "O cliente foi criado com sucesso") // Cabeçalho personalizado(só para praticar funcionalidades)
                .body(novoCliente); // Faz o objeto criado ser retornado no corpo da resposta.
    }

    @PostMapping
    public String deletarCliente() {
        // Lógica para deletar um cliente pode ser adicionada aqui
        return "Cliente deletado com sucesso!";
    }

    @GetMapping("/teste")
    public String teste() {
        return "O servidor está funcionando!";
    }




}
