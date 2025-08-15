package com.igor.cartaoIgor.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Delega a geração do ID para o banco de dados
    private Long id;

    @NotBlank(message = "O nome do cliente é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF do cliente é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "O e-mail do cliente é obrigatório")
    // @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "O e-mail deve ser válido")
    @Email(message = "O e-mail deve ser válido")
    @Column(unique = true)
    private String email;

    @Past(message = "A data de nascimento deve ser anterior à data atual")
    @Column(name = "data_nascimento", nullable = false)
    @Schema(description = "Data de nascimento do cliente no formato YYYY-MM-DD", example = "1990-01-01")
    private LocalDate dataNascimento;

    // Construtores

    public Cliente() {
    }

    public Cliente(Long id, String nome, String cpf, String email, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
    
    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String toString() {
        return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", dataNascimento="
                + dataNascimento + "]";
    }
}
