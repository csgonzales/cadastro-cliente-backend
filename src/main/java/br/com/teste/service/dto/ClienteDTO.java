package br.com.teste.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.teste.model.Risco;
import lombok.Data;

@Data
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -2525344829807033634L;

	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal limiteCredito;
	
	@NotNull
	private Risco risco;
	
	
	
}
