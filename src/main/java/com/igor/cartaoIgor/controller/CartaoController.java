package com.igor.cartaoIgor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartao")
public class CartaoController {
    
    @GetMapping("/hello")
    public String helloCartao() {
        return "Olá da API de Cartões do Igor!";
    }
}
