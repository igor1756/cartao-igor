package com.igor.cartaoIgor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para cadastro de cartão")
public class CartaoCadastroDTO {
    @Schema(description = "Nome do cartão", example = "Cartão de Crédito")
    @NotBlank(message = "O nome do cartão é obrigatório")
    @Size(max = 100, message = "O nome do cartão deve ter no máximo 100 caracteres")
    private String nome;

    @Schema(description = "Tipo do cartão", example = "Credito")
    @NotBlank(message = "O tipo do cartão é obrigatório")
    @Pattern(regexp = "^(Debito|Credito)$", message = "O tipo do cartão deve ser 'Debito' ou 'Credito'")
    @Size(max = 50, message = "O tipo do cartão deve ter no máximo 50 caracteres")
    private String tipo;

    @NotNull(message = "A anuidade do cartão é obrigatória")
    private float anuidade;

    @NotBlank(message = "A bandeira do cartão é obrigatória")
    @Size(max = 50, message = "A bandeira do cartão deve ter no máximo 50 caracteres")
    private String bandeira;

    // construtor
    public CartaoCadastroDTO() {
    }

    // getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getAnuidade() {
        return anuidade;
    }

    public void setAnuidade(float anuidade) {
        this.anuidade = anuidade;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

}
