package com.igor.cartaoIgor.enums;

public enum CartaoTipo {
    CREDITO("Crédito"),
    DEBITO("Débito");

    private final String descricao;

    private CartaoTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
