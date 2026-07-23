package com.generation.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ecommerce.model.Pedido;
import com.generation.ecommerce.repository.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;


    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {

        return ResponseEntity.ok(repository.findAll());

    }


    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id) {

        return repository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());

        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> put(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {

        return repository.findById(id)
                .map(resposta -> {

                    resposta.setProduto(pedido.getProduto());
                    resposta.setDescricao(pedido.getDescricao());
                    resposta.setValor(pedido.getValor());
                    resposta.setCliente(pedido.getCliente());

                    return ResponseEntity.ok(repository.save(resposta));

                })
                .orElse(ResponseEntity.notFound().build());

    }
    
    @PostMapping
    public ResponseEntity<Pedido> post(@RequestBody Pedido pedido) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(pedido));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}