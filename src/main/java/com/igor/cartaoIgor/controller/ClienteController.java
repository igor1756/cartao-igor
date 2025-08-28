package com.igor.cartaoIgor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.cartaoIgor.dto.ClienteCadastroDTO;
import com.igor.cartaoIgor.model.Cliente;
import com.igor.cartaoIgor.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // listar todos os clientes
    @GetMapping
    @Operation(summary = "Listar todos os clientes", responses = {
            @ApiResponse(responseCode = "200", description = "Clientes listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
    })
    public Iterable<Cliente> getAllClientes() {
        return clienteService.listarClientes();
    }

    // listar cliente pelo id
    @GetMapping("/{id}")
    @Operation(summary = "Listar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.listarClientePorId(id);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // buscar cliente pelo nome
    @GetMapping("/nome/{nome}")
    @Operation(summary = "Listar um cliente pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Cliente> listarClientePorNome(@PathVariable String nome) {
        Cliente cliente = clienteService.listarClientePorNome(nome);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // buscar cliente pelo cpf
    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Listar um cliente pelo CPF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Cliente> listarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.listarClientePorCpf(cpf);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // buscar cliente pelo email
    @GetMapping("/email/{email}")
    @Operation(summary = "Listar um cliente pelo email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado."),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    public ResponseEntity<Cliente> listarClientePorEmail(@PathVariable String email) {
        Cliente cliente = clienteService.listarClientePorEmail(email);
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // cadastrar cliente
    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody ClienteCadastroDTO clienteDto) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome(clienteDto.getNome());
            cliente.setCpf(clienteDto.getCpf());
            cliente.setEmail(clienteDto.getEmail());
            cliente.setDataNascimento(clienteDto.getDataNascimento());
            Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // remover cliente
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um cliente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
        try {
            clienteService.deletarCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
