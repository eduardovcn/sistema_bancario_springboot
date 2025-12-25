package com.eduardo.banco_crud.service;

import com.eduardo.banco_crud.model.Cliente;
import com.eduardo.banco_crud.model.Conta;
import com.eduardo.banco_crud.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor // Cria o construtor com os campos finais automaticamente, usando o Lombok.
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteService clienteService;

    @Transactional
    public Conta criarConta(String cpfCliente) {

        Cliente cliente = clienteService.buscarClientePorCpf(cpfCliente);

        Conta novaConta = new Conta();
        novaConta.setCliente(cliente);
        // Garante que o saldo inicial seja zero, mesmo que o construtor, eventualmente, mude.
        novaConta.setSaldo(BigDecimal.ZERO);
        novaConta = contaRepository.save(novaConta);

        String numeroFormatado = String.format("%04d", novaConta.getId());
        novaConta.setNumeroConta(numeroFormatado);

        return contaRepository.save(novaConta);
    }


    @Transactional
    public Conta depositar(Long contaId, BigDecimal valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.depositar(valor);
        return contaRepository.save(conta);
    }

    @Transactional
    public Conta sacar(Long contaId, BigDecimal valor) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.sacar(valor);
        return contaRepository.save(conta);
    }

    @Transactional
    public BigDecimal consultarSaldo(String numeroConta) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return conta.getSaldo();
    }

    @Transactional
    public String consultarExtrato(Long contaId) {
        Conta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return "Extrato da conta " + conta.getNumeroConta() + ": Saldo atual = " + conta.getSaldo();
    }


    public List<Conta> listarContas(Long clienteId) {
        return contaRepository.findByClienteId(clienteId);
    }

    @Transactional
    public Conta transferir(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        Conta contaOrigem = contaRepository.findById(contaOrigemId)
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Conta contaDestino = contaRepository.findById(contaDestinoId)
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        contaOrigem.transferir(valor, contaDestino);

        contaRepository.save(contaDestino);
        return contaRepository.save(contaOrigem);
    }

    @Transactional
    public void encerrarConta(String numeroConta) {
        Conta conta = contaRepository.findByNumeroConta(numeroConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (conta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalStateException("Conta não pode ser encerrada pois ainda há saldo disponível.");
        }
        contaRepository.delete(conta);
    }

}