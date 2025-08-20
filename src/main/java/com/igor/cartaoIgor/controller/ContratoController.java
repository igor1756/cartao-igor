package com.igor.cartaoIgor.controller;

    
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.cartaoIgor.dto.ContratoCriaDTO;
import com.igor.cartaoIgor.model.Contrato;
import com.igor.cartaoIgor.service.ContratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contratos")
@Tag(name = "Contratos", description = "Endpoints para gerenciamento de contratos")
public class ContratoController {

    private final ContratoService contratoService;

    @Autowired
    public ContratoController(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    // criar contrato
    @PostMapping
    @Operation(summary = "Criar um novo contrato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contrato criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação ou IDs de cliente/cartão inválidos")
    })
    public ResponseEntity<Contrato> criarContrato(@Valid @RequestBody ContratoCriaDTO contratoCriaDTO) {
        try {
            Contrato novoContrato = contratoService.criarContrato(contratoCriaDTO.getClienteId(),
                    contratoCriaDTO.getCartaoId());
            return new ResponseEntity<>(novoContrato, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // listar contrastos
    @GetMapping
    @Operation(summary = "Listar todos os contratos")
    public Iterable<Contrato> listarContratos() {
        return contratoService.listarContratos();
    }

    // remover contrato
    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um contrato pelo ID")
    public ResponseEntity<Void> removerContrato(@PathVariable Long id) {
        try {
            contratoService.removerContrato(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // encerrar contrato
    @PutMapping("/{id}/encerrar")
    @Operation(summary = "Encerrar um contrato pelo ID")
    public ResponseEntity<Void> encerrarContrato(@PathVariable Long id) {
        contratoService.encerrarContrato(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

