package com.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.ecommerce.model.Pedido;
import com.generation.ecommerce.repository.PedidoRepository;


@Service
public class PedidoService {


    @Autowired
    private PedidoRepository repository;



    public List<Pedido> findAll() {

        return repository.findAll();

    }



    public Optional<Pedido> findById(Long id) {

        return repository.findById(id);

    }



    public Pedido save(Pedido pedido) {

        return repository.save(pedido);

    }



    public void delete(Long id) {

        repository.deleteById(id);

    }



    public List<Pedido> findByCliente(String nome) {

        return repository.findAllByClienteNomeContainingIgnoreCase(nome);

    }


}
