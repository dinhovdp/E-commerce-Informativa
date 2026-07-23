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

	import com.generation.ecommerce.model.Cliente;
	import com.generation.ecommerce.repository.ClienteRepository;



	@RestController
	@RequestMapping("/clientes")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public class ClienteController {
		

	    @Autowired
	    private ClienteRepository repository;

	    @GetMapping
	    public ResponseEntity<List< Cliente>> getAll() {
	        return ResponseEntity.ok(repository.findAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity< Cliente> getById(@PathVariable Long id) {

	        return repository.findById(id)
	                .map(resposta -> ResponseEntity.ok(resposta))
	                .orElse(ResponseEntity.notFound().build());

	    }

	    @GetMapping("/tipo/{tipo}")
	    public ResponseEntity<List< Cliente>> getByTipo(@PathVariable String tipo) {

	        return ResponseEntity.ok(repository.findAllByTipoContainingIgnoreCase(tipo));

	    }

	    @PostMapping
	    public ResponseEntity<Cliente> post(@RequestBody  Cliente  cliente) {

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(repository.save(cliente));

	    }

	    @PutMapping("/{id}")
	    public ResponseEntity< Cliente> put(
	            @PathVariable Long id,
	            @RequestBody Cliente categoria) {

	        return repository.findById(id)
	                .map(resposta -> {

	                    resposta.setTipo( cliente.getTipo());
	                    resposta.setDescricao(cliente.getDescricao());

	                    return ResponseEntity.ok(repository.save(resposta));

	                })
	                .orElse(ResponseEntity.notFound().build());
	    }
	        
	    

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {

	        repository.deleteById(id);

	    

	}
}
