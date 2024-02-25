package br.com.organicxpto.pedidos;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private UUID idCliente;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Item>  itens;

	public Pedido() {
		super();
	}

	public Pedido(UUID id, UUID idCliente, List<Item> itens) {
		this.id = id;
		this.idCliente = idCliente;
		this.itens = itens;
	}

	public UUID getId() {
		return id;
	}

	public UUID getIdCliente() {
		return idCliente;
	}

	public List<Item> getItens() {
		return itens;
	}
}
