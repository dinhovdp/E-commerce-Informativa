package com.generation.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
	

	import com.generation.ecommerce.model.Cliente;

	public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	}
