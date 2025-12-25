package com.eduardo.banco_crud.controller;


import com.eduardo.banco_crud.model.Conta;
import com.eduardo.banco_crud.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/area_conta")
public class ContaController {

    private final ContaService contaService;

    @PostMapping("/nova_conta")
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta){
        Conta novaConta = contaService.criarConta(conta.getCliente().getCpf());

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("X-SISTEMA-BANCARIO", "A conta foi criada com sucesso")
                .body(novaConta);
    }

    @DeleteMapping("/encerrar_conta/{numeroConta}")
    public ResponseEntity<Conta> encerrarConta(@PathVariable String numeroConta){
        contaService.encerrarConta(numeroConta);

        return ResponseEntity.noContent().build(); // Retorna 204 No Content

    }

    @GetMapping("/listar_contas/{clienteId}")
    public ResponseEntity<List<Conta>> listarContas(@PathVariable Long clienteId){
        List<Conta> contas = contaService.listarContas(clienteId);

        return ResponseEntity.ok(contas); // Retorna 200 OK com a lista de contas no corpo da resposta.
    }

    @PostMapping("/depositar/{id}")
    public ResponseEntity<Conta> depositar(@PathVariable Long id, @RequestBody BigDecimal valor) {
        Conta contaAtualizada = contaService.depositar(id, valor);

        return ResponseEntity.ok(contaAtualizada); // Retorna 200 OK com a conta atualizada no corpo da resposta.
    }

    @PostMapping("/sacar/{id}")
    public ResponseEntity<Conta> sacar(@PathVariable Long id, @RequestBody BigDecimal valor) {
        Conta contaAtualizada = contaService.sacar(id, valor);

        return ResponseEntity.ok(contaAtualizada); // Retorna 200 OK com a conta atualizada no corpo da resposta.
    }

    @GetMapping("/consultar_saldo/{numeroConta}")
    public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String numeroConta){

        BigDecimal saldo = contaService.consultarSaldo(numeroConta);

        return ResponseEntity.ok(saldo);
    }


}
