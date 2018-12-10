package br.com.teste.service;

import br.com.teste.service.dto.ClienteDTO;
import br.com.teste.service.dto.ClienteRespostaDTO;

public interface ClienteService {

	ClienteRespostaDTO adicionar(ClienteDTO clienteDTO);
	
}
