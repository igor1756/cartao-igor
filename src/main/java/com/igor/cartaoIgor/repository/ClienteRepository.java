package com.igor.cartaoIgor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.cartaoIgor.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    // metodos implementados pelo spring
        // save() - para salvar ou atualizar
        // findById(Long id) - para buscar por ID
        // findAll() - para buscar todos os objetos
        // deleteById(Long id) - para deletar pelo ID
    
    Optional<Cliente> findByNome(String nome);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByEmail(String email);
}
