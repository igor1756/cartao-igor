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

import com.igor.cartaoIgor.dto.CartaoCadastroDTO;
import com.igor.cartaoIgor.model.Cartao;
import com.igor.cartaoIgor.service.CartaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cartoes")
@Tag(name = "Cartões", description = "Endpoints para gerenciamento de cartões")
public class CartaoController {

    private final CartaoService cartaoService;

    @Autowired
    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    // listar todos os cartoes
    @GetMapping
    @Operation(summary = "Listar todos os cartões", responses = {
            @ApiResponse(responseCode = "200", description = "Cartões listados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cartao.class))),
    })
    public Iterable<Cartao> listarCartoes() {
        return cartaoService.listarCartoes();
    }

    // listar cartao pelo id
    @GetMapping("/{id}")
    @Operation(summary = "Listar um cartão pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartão encontrado"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    public ResponseEntity<Cartao> listarCartaoPorId(@PathVariable Long id) {
        Cartao cartao = cartaoService.listarCartaoPorId(id);
        if (cartao != null) {
            return new ResponseEntity<>(cartao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // cadastrar cartao
    @PostMapping
    @Operation(summary = "Cadastrar um novo cartão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    public ResponseEntity<Cartao> cadastrarCartao(@Valid @RequestBody CartaoCadastroDTO cartaoDto) {
        Cartao cartaoCadastrado = cartaoService.cadastrarCartao(cartaoDto);
        return new ResponseEntity<>(cartaoCadastrado, HttpStatus.CREATED);
    }

    // remover cartao
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um cartão pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cartão removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cartão não encontrado")
    })
    public ResponseEntity<Void> removerCartao(@PathVariable Long id) {
        try {
            cartaoService.removerCartao(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
