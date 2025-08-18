package com.igor.cartaoIgor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igor.cartaoIgor.dto.CartaoCadastroDTO;
import com.igor.cartaoIgor.enums.CartaoTipo;
import com.igor.cartaoIgor.exception.CartaoTipoInvalidoException;
import com.igor.cartaoIgor.model.Cartao;
import com.igor.cartaoIgor.repository.CartaoRepository;

@Service
public class CartaoService {
    // Injeção de dependência do CartaoRepository
    private final CartaoRepository cartaoRepository;

    @Autowired
    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    // cadastrar cartao
    public Cartao cadastrarCartao(CartaoCadastroDTO cartaoDto) {
        CartaoTipo tipoCartao;
        try {
            // converte string para enum
            tipoCartao = CartaoTipo.valueOf(cartaoDto.getTipo().toUpperCase());
            
        } catch (IllegalArgumentException e) {
            // se o argumento for invalido captura a excecao e lanca uma excecao especifica
            throw new CartaoTipoInvalidoException("Tipo de cartão inválido: " + cartaoDto.getTipo() + ".");
        }
        Cartao cartaoNovo = new Cartao();
        cartaoNovo.setNome(cartaoDto.getNome());
        cartaoNovo.setTipo(tipoCartao);
        cartaoNovo.setAnuidade(cartaoDto.getAnuidade());
        cartaoNovo.setBandeira(cartaoDto.getBandeira());
        return cartaoRepository.save(cartaoNovo);
    }

    // listar todos os cartoes
    public Iterable<Cartao> listarCartoes() {
        return cartaoRepository.findAll();
    }

    // listar cartao pelo id
    public Cartao listarCartaoPorId(Long id) {
        return cartaoRepository.findById(id).orElse(null);
    }

    // atualizar cartao
    public Cartao atualizarCartao(Long id, Cartao cartaoInformado) {
        Cartao cartaoExistente = cartaoRepository.findById(id).orElse(null);
        if (cartaoExistente == null) {
            throw new IllegalArgumentException("Cartao não encontrado. ID: " + id);
        }
        cartaoExistente.setNome(cartaoInformado.getNome());
        cartaoExistente.setTipo(cartaoInformado.getTipo());
        cartaoExistente.setAnuidade(cartaoInformado.getAnuidade());
        cartaoExistente.setBandeira(cartaoInformado.getBandeira());
        return cartaoRepository.save(cartaoExistente);
    }

    // remover cartão
    public void removerCartao(Long id) {
        try {
            cartaoRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Id não encontrado");
        }
    }
}