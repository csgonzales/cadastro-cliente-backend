package br.com.teste.service.dto;

import lombok.Getter;

@Getter
public class ClienteRespostaDTO {

	private Long id;
	private Integer taxaJuros;
	
	private ClienteRespostaDTO() {}
	
	public static ClienteRespostaDTO comId(Long id) {
		ClienteRespostaDTO resposta = new ClienteRespostaDTO();
		resposta.id = id;
		return resposta;
	}
	
	public ClienteRespostaDTO comTaxaJuros(Integer taxaJuros) {
		this.taxaJuros = taxaJuros;
		return this; 
	}
	
}
