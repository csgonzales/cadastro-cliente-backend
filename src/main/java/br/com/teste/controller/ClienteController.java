package br.com.teste.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Risco;
import br.com.teste.service.ClienteService;
import br.com.teste.service.dto.ClienteDTO;
import br.com.teste.service.dto.ClienteRespostaDTO;

@RestController
@RequestMapping("clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	
	@PostMapping
	public ClienteRespostaDTO postCliente(@RequestBody @Valid	ClienteDTO clienteDTO) {		
		return clienteService.adicionar(clienteDTO);
	}
	
	@GetMapping("/riscos")
	public Risco[] getRiscos() {
		return Risco.values();
	}
	
}
