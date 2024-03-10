package br.com.organicxpto.estoque.Produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Integer quantidade;

    private LocalDateTime dataCriacao;

    public void removerEstoque(Integer quantidade) {

        if (quantidade <= 0) {
            throw new UnsupportedOperationException("Valor inválido");
        } else if ((this.quantidade - quantidade <= 0)) {
            throw new UnsupportedOperationException("Estoque insuficiente");
        } else {
            this.quantidade = this.quantidade - quantidade;
        }

    }
}
