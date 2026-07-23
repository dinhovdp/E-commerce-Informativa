package com.generation.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.ecommerce.model.Cliente;
import com.generation.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    // Buscar todos os clientes
    public List<Cliente> findAll() {

        return repository.findAll();

    }


    // Buscar cliente por ID
    public Optional<Cliente> findById(Long id) {

        return repository.findById(id);

    }


    // Salvar cliente
    public Cliente save(Cliente cliente) {

        return repository.save(cliente);

    }


    // Atualizar cliente
    public Cliente update(Cliente cliente) {

        return repository.save(cliente);

    }


    // Excluir cliente
    public void delete(Long id) {

        repository.deleteById(id);

    }


    // Pesquisa personalizada por nome
    public List<Cliente> findAllByNome(String nome) {

        return repository.findAllByNomeContainingIgnoreCase(nome);

    }

}