package com.generation.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.ecommerce.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAllByNomeContainingIgnoreCase(String nome);

}