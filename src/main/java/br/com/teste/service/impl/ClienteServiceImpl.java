package br.com.teste.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.model.Cliente;
import br.com.teste.model.Risco;
import br.com.teste.repository.ClienteRepository;
import br.com.teste.service.ClienteService;
import br.com.teste.service.dto.ClienteDTO;
import br.com.teste.service.dto.ClienteRespostaDTO;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;
	
	@Override
	public ClienteRespostaDTO adicionar(ClienteDTO clienteDTO) {
		Cliente novoCliente = repository.save(novoCliente(clienteDTO));
		return ClienteRespostaDTO
				.comId(novoCliente.getId())
				.comTaxaJuros(novoCliente.getTaxaJuros());
	}

	/**
	 * Responsável por criar novo cliente para persistência, seguindo as seguintes regras:
	 *  
	 * - Todas informações devem ser persistidas mais um campo indicando a taxa de juros.
	 * - Todos campos são obrigatórios
	 * - Se o risco for do tipo A manter os dados informados
	 * - Se o risco for do tipo B, a taxa de juros deve ser de 10%
	 * - Se o risco for do tipo C, a taxa de juros deve ser de 20%
	 * 
	 * @param clienteDTO
	 * @return cliente para persistência
	 */
	private Cliente novoCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setLimiteCredito(clienteDTO.getLimiteCredito());
		
		Risco risco = clienteDTO.getRisco();
		cliente.setRisco(risco);
		cliente.setTaxaJuros(risco.getPercentualJuros());
		return cliente;
	}
	
}
