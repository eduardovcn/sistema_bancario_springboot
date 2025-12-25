package com.eduardo.banco_crud.controller;


import com.eduardo.banco_crud.model.Cliente;
import com.eduardo.banco_crud.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/area_cliente")
public class ClienteController {

    private final ClienteService clienteService;

    // Para testar se o serviço está funcionando
    @GetMapping("/ola")
    public String ola(String nome) {

        nome = "Eduardo";

        return clienteService.ola(nome);
    }

    @PostMapping("/novo_cliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {

        Cliente novoCliente = clienteService.criarCliente(cliente);


        return ResponseEntity.status(HttpStatus.CREATED) // Cria o status HTTP 201
                .header("X-SISTEMA-BANCARIO", "O cliente foi criado com sucesso") // Cabeçalho personalizado(só para praticar funcionalidades)
                .body(novoCliente); // Faz o objeto criado ser retornado no corpo da resposta.
    }

    @DeleteMapping("/encerrar_cliente/{id}") // {id} indica um valor dinâmico na URL.
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

    @GetMapping("/buscar_por_cpf/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok(cliente); // Retorna 200 OK com o cliente no corpo da resposta.
    }


    @GetMapping("/teste")
    public String teste() {
        return "O servidor está funcionando!";
    }




}
