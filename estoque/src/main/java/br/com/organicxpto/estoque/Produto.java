package br.com.organicxpto.estoque;

import java.math.BigDecimal;

public class Produto {

	private Long id;

	private String nome;

	private BigDecimal quantidade;

	public Produto(Long id, String nome, BigDecimal quantidade) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public void removerEstoque(BigDecimal quantidade) {

		if(quantidade.compareTo(BigDecimal.ZERO) <= 0)
		{
			throw new UnsupportedOperationException("Valor inválido");
		}
		else if(this.quantidade.subtract(quantidade).compareTo(BigDecimal.ZERO) <= 0){

			throw new UnsupportedOperationException("Estoque insuficiente");
		}
		else {
			this.quantidade = this.quantidade.subtract(quantidade);
		}

	}
}
