package br.com.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teste.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	
}
