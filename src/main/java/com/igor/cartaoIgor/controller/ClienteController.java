package com.igor.cartaoIgor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> getAllClientes() {
        return ResponseEntity.ok("Get all clientes");
    }

    // cadastrar cliente
    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    public ResponseEntity<Cliente> cadastrarCliente(@Valid @RequestBody Cliente cliente) {
        try {
            Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
            return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
