package com.igor.cartaoIgor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para criação de contrato")
public class ContratoCriaDTO {
    @Schema(description = "ID do cliente", example = "1")
    @NotNull(message = "O ID do cliente é obrigatório")
    @Min(1)
    private Long clienteId;

    @Schema(description = "ID do cartão", example = "1")
    @NotNull(message = "O ID do cartão é obrigatório")
    @Min(1)
    private Long cartaoId;

    // construtor
    public ContratoCriaDTO() {
    }

    // getters and setters

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(Long cartaoId) {
        this.cartaoId = cartaoId;
    }

}
