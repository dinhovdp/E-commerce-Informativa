package com.generation.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.generation.ecommerce.model.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByClienteNomeContainingIgnoreCase(String nome);

}