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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.ecommerce.model.Pedido;
import com.generation.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {

	@Autowired
	private PedidoService service;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {

		return ResponseEntity.ok(service.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable Long id) {

		return service.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());

	}

	// Pesquisa pedidos pelo nome do cliente
	// Exemplo: /pedidos/pesquisa?cliente=Maria
	@GetMapping("/pesquisa")
	public ResponseEntity<List<Pedido>> pesquisarPorCliente(@RequestParam String cliente) {

		return ResponseEntity.ok(service.findByCliente(cliente));

	}

	@PostMapping
	public ResponseEntity<Pedido> post(@RequestBody Pedido pedido) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pedido));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Pedido> put(@PathVariable Long id, @RequestBody Pedido pedido) {

		return service.findById(id).map(resposta -> {

			resposta.setProduto(pedido.getProduto());
			resposta.setDescricao(pedido.getDescricao());
			resposta.setValor(pedido.getValor());
			resposta.setCliente(pedido.getCliente());

			return ResponseEntity.ok(service.save(resposta));

		}).orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);

		return ResponseEntity.noContent().build();

	}

}