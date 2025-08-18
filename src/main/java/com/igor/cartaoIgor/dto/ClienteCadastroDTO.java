package com.igor.cartaoIgor.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para cadastro de cliente")
public class ClienteCadastroDTO {

    @NotBlank(message = "O nome do cliente é obrigatório")
    @Schema(description = "Nome do cliente", example = "João Silva")
    @Size(max = 100, message = "O nome do cliente deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O CPF do cliente é obrigatório")
    @Schema(description = "CPF do cliente", example = "12345678901")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "O e-mail do cliente é obrigatório")
    @Schema(description = "Email do cliente", example = "joao@email.com")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 50, message = "O e-mail deve ter no máximo 50 caracteres")
    private String email;

    @Schema(description = "Data de nascimento do cliente", example = "1990-01-01")
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser anterior à data atual")
    private LocalDate dataNascimento;

    // construtor
    public ClienteCadastroDTO() {
    }

    // getters and setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
