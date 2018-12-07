package br.com.teste.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 3761476940072414665L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nome;
	
	@Positive
	private BigDecimal limiteCredito;
	
	@NotNull
	private Risco risco;
	
	@PositiveOrZero
	private Integer taxaJuros = 0;

}
