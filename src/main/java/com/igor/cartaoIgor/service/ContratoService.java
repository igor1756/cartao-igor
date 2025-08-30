package com.igor.cartaoIgor.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.cartaoIgor.model.Cartao;
import com.igor.cartaoIgor.model.Cliente;
import com.igor.cartaoIgor.model.Contrato;
import com.igor.cartaoIgor.repository.ContratoRepository;

import jakarta.transaction.Transactional;

@Service
public class ContratoService {

    private final ContratoRepository contratoRepository;
    private final ClienteService clienteService;
    private final CartaoService cartaoService;

    @Autowired
    public ContratoService(ContratoRepository contratoRepository, ClienteService clienteService,
            CartaoService cartaoService) {
        this.contratoRepository = contratoRepository;
        this.clienteService = clienteService;
        this.cartaoService = cartaoService;
    }

    // criar contrato
    @Transactional
    public Contrato criarContrato(Long clienteId, Long cartaoId) {
        Cliente cliente = clienteService.listarClientePorId(clienteId);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + clienteId);
        }

        Cartao cartao = cartaoService.listarCartaoPorId(cartaoId);
        if (cartao == null) {
            throw new IllegalArgumentException("Cartão não encontrado com o ID: " + cartaoId);
        }

        Contrato contrato = new Contrato(cliente, cartao);
        return contratoRepository.save(contrato);
    }

    // listar contratos
    @Transactional
    public Iterable<Contrato> listarContratos() {
        return contratoRepository.findAll();
    }

    // buscar contratos pelo id do cliente
    @Transactional
    public List<Contrato> listarContratosPorClienteId(Long clienteId) {
        return contratoRepository.findByClienteId(clienteId);
    }

    // buscar contrato pelo id
    @Transactional
    public Contrato listarContratoPorId(Long id) {
        return contratoRepository.findById(id).orElse(null);
    }

    // remover contrato
    @Transactional
    public void removerContrato(Long id) {
        contratoRepository.deleteById(id);
    }

    // encerrar contrato
    @Transactional
    public void encerrarContrato(Long id) {
        Contrato contrato = contratoRepository.findById(id).orElse(null);

        if (contrato != null) {
            // alterar o status do contrato a ser encerrado
            contrato.setStatus("ENCERRADO");
            contrato.setDataFim(LocalDate.now());
            contratoRepository.save(contrato);
        } else {
            throw new IllegalArgumentException("Contrato não encontrado com o ID: " + id);
        }
    }


}
