package com.igor.cartaoIgor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.cartaoIgor.model.Cliente;
import com.igor.cartaoIgor.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    // Injeção de dependência do ClienteRepository
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // cadastrar cliente
    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        // valida se o cliente existe
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        // salva o cliente
        return clienteRepository.save(cliente);
    }

    // listar todos os clientes
    @Transactional
    public Iterable<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // listar cliente pelo id
    @Transactional
    public Cliente listarClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    // buscar cliente pelo nome
    @Transactional
    public Cliente listarClientePorNome(String nome) {
        return clienteRepository.findByNome(nome).orElse(null);
    }

    // buscar cliente pelo cpf
    @Transactional
    public Cliente listarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElse(null);
    }

    // buscar cliente pelo email
    @Transactional
    public Cliente listarClientePorEmail(String email) {
        return clienteRepository.findByEmail(email).orElse(null);
    }

    // atualizar cliente
    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteInformado) {

        // 1. valida se o cliente existe pelo id
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado. ID: " + id);
        }
        // 2. se o id do cliente existe, verificar se o cpf existe para outro id
        // cpf do cliente informado eh diferente de nulo &&
        // cpf do cliente informado eh diferente do cpf do cliente do id informado &&
        // cpf informado esta presente em outro id
        if (clienteInformado.getCpf() != null && !clienteInformado.getCpf().equals(clienteExistente.getCpf())
                && clienteRepository.findByCpf(clienteInformado.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
            // TODO: retornar id do cliente que possui o cpf informado
        }
        // 3. se o email do cliente informado existe, verificar se esta cadastrado por
        // outro id
        if (clienteInformado.getEmail() != null && !clienteInformado.getEmail().equals(clienteExistente.getEmail())
                && clienteRepository.findByEmail(clienteInformado.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
            // TODO: retornar id do cliente que possui o email informado
        }
        // 4. Atualizar os dados do clienteInformado
        clienteExistente.setNome(clienteInformado.getNome());
        clienteExistente.setCpf(clienteInformado.getCpf());
        clienteExistente.setEmail(clienteInformado.getEmail());
        clienteExistente.setDataNascimento(clienteInformado.getDataNascimento());
        // 5. Salvar o cliente atualizado
        return clienteRepository.save(clienteExistente);
    }

    // deletar cliente
    @Transactional
    public void deletarCliente(Long id) {
        // verificar se o cliente existe
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado. ID: " + id);
        }
    }
}