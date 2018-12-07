package br.com.teste.model;


/**
 * Representação dos riscos em sistema, seguindo as seguintes regras de valor: 
 * 
 * - Se o risco for do tipo A manter os dados informados
 * - Se o risco for do tipo B, a taxa de juros deve ser de 10%
 * - Se o risco for do tipo C, a taxa de juros deve ser de 20%
 * 
 * @author Caio
 *
 */
public enum Risco {

	A(0), B(10), C(20);

	private Risco(Integer percentualJuros) {
		this.percentualJuros = percentualJuros;
	}
	
	private Integer percentualJuros;
	
	public Integer getPercentualJuros() {
		return percentualJuros;
	}
	
}
