package br.com.organicxpto.pedidos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long idProduct;

	private BigDecimal quantidade;

	public Item() {
		super();
	}

	public Item(Long idProduct, BigDecimal quantidade) {
		this.idProduct = idProduct;
		this.quantidade = quantidade;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}
}
