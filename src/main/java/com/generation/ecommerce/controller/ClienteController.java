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

import com.generation.ecommerce.model.Cliente;
import com.generation.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {

		return ResponseEntity.ok(service.findAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) {

		return service.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/pesquisa")
	public ResponseEntity<List<Cliente>> getByNome(@RequestParam String nome) {

		return ResponseEntity.ok(service.findAllByNome(nome));

	}

	@PostMapping
	public ResponseEntity<Cliente> post(@RequestBody Cliente cliente) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cliente));

	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> put(@PathVariable Long id, @RequestBody Cliente cliente) {

		return service.findById(id).map(resposta -> {

			resposta.setNome(cliente.getNome());
			resposta.setEmail(cliente.getEmail());

			return ResponseEntity.ok(service.save(resposta));

		}).orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		service.delete(id);
		

		return ResponseEntity.noContent().build();

	}

}