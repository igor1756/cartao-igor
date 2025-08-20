package com.igor.cartaoIgor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.cartaoIgor.model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    // metodos implementados pelo spring
    // save() - para salvar ou atualizar
    // findById(Long id) - para buscar por ID
    // findAll() - para buscar todos os objetos
    // deleteById(Long id) - para deletar pelo ID



}
