package br.com.teste.service.impl;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.teste.model.Cliente;
import br.com.teste.model.Risco;
import br.com.teste.repository.ClienteRepository;
import br.com.teste.service.ClienteService;
import br.com.teste.service.dto.ClienteDTO;
import br.com.teste.service.dto.ClienteRespostaDTO;


/**	Todas informações devem ser persistidas mais um campo indicando a
  *	taxa de juros.
  *
  * - Todos campos são obrigatórios (Já tratado com validations)
  *	- Se o risco for do tipo A manter os dados informados
  *	- Se o risco for do tipo B, a taxa de juros deve ser de 10%
  *	- Se o risco for do tipo C, a taxa de juros deve ser de 20%
  */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteServiceImplTests {
	
	@TestConfiguration
	static class ClienteServiceImplTestConfiguration {
		
		@Bean
		public ClienteService clienteService() {
			return new ClienteServiceImpl();
		}
	}
	
	
	@MockBean
	ClienteRepository repository;
	
	@Autowired
	ClienteService clienteService;
	
	
	private ClienteDTO clienteComRisco(Risco risco) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setNome("Teste");
		clienteDTO.setLimiteCredito(BigDecimal.valueOf(1000));
		clienteDTO.setRisco(risco);
		return clienteDTO;
	}
	
	private Cliente clienteRetornado(Risco risco) {
		Cliente cliente = clienteEsperado(risco);
		cliente.setId(1L);
		return cliente;
	}
	
	private Cliente clienteEsperado(Risco risco) {
		Cliente cliente = new Cliente();
		cliente.setNome("Teste");
		cliente.setLimiteCredito(BigDecimal.valueOf(1000));
		cliente.setRisco(risco);
		cliente.setTaxaJuros(risco.getPercentualJuros());
		return cliente;
	}
	
	//Se o risco for do tipo A manter os dados informados
	@Test
	public void verificarConsistenciaRiscoA() {
		ClienteDTO cliente = clienteComRisco(Risco.A);
		
		when(repository.save(eq(clienteEsperado(Risco.A)))).thenReturn(clienteRetornado(Risco.A));
		ClienteRespostaDTO respostaDTO = clienteService.adicionar(cliente);
		
		assertEquals(1L, respostaDTO.getId().longValue());
		assertEquals(0, respostaDTO.getTaxaJuros().intValue());
		
	}
	
	//Se o risco for do tipo B, a taxa de juros deve ser de 10%
	@Test
	public void verificarConsistenciaRiscoB() {
		ClienteDTO cliente = clienteComRisco(Risco.B);
		
		when(repository.save(eq(clienteEsperado(Risco.B)))).thenReturn(clienteRetornado(Risco.B));
		ClienteRespostaDTO respostaDTO = clienteService.adicionar(cliente);
		
		assertEquals(1L, respostaDTO.getId().longValue());
		assertEquals(10, respostaDTO.getTaxaJuros().intValue());
	}
	
	//Se o risco for do tipo C, a taxa de juros deve ser de 20%
	@Test
	public void verificarConsistenciaRiscoC() {
		ClienteDTO cliente = clienteComRisco(Risco.C);
		
		when(repository.save(eq(clienteEsperado(Risco.C)))).thenReturn(clienteRetornado(Risco.C));
		ClienteRespostaDTO respostaDTO = clienteService.adicionar(cliente);
		
		assertEquals(1L, respostaDTO.getId().longValue());
		assertEquals(20, respostaDTO.getTaxaJuros().intValue());
	}
	
}
