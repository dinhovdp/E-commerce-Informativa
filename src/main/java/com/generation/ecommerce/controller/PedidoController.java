package com.generation.ecommerce.controller;

public class PedidoController {

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import com.generation.gameshow.model.Produto;
	import com.generation.gameshow.repository.ProdutoRepository;

	@RestController
	@RequestMapping("/produtos")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public class ProdutoController {
		
		@Autowired
		private ProdutoRepository repository;
		
		@GetMapping
		public ResponseEntity<List<Produto>> getAll() {

		    return ResponseEntity.ok(repository.findAll());

		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Produto> getById(@PathVariable Long id) {

		    return repository.findById(id)
		            .map(resposta -> ResponseEntity.ok(resposta))
		            .orElse(ResponseEntity.notFound().build());

		}
		
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {

		    return ResponseEntity.ok(
		            repository.findAllByNomeContainingIgnoreCase(nome));

		}
		
		@PostMapping
		public ResponseEntity<Produto> post(@RequestBody Produto produto) {

		    return ResponseEntity.status(HttpStatus.CREATED)
		            .body(repository.save(produto));

		}
		
		
		@PutMapping("/{id}")
		public ResponseEntity<Produto> put(
		        @PathVariable Long id,
		        @RequestBody Produto produto) {

		    return repository.findById(id)
		            .map(resposta -> {

		                resposta.setNome(produto.getNome());
		                resposta.setFoto(produto.getFoto());
		                resposta.setQuantidade(produto.getQuantidade());
		                resposta.setPreco(produto.getPreco());
		                resposta.setCategoria(produto.getCategoria());

		                return ResponseEntity.ok(repository.save(resposta));

		            })
		            .orElse(ResponseEntity.notFound().build());

		}
		
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id) {

		    repository.deleteById(id);

		}
		
		
		
		
	}
	
}
