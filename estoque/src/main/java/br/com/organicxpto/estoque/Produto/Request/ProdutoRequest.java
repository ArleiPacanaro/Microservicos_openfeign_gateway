package br.com.organicxpto.estoque.Produto.Request;

import java.math.BigDecimal;

    public record ProdutoRequest(String nome, String descricao, BigDecimal preco, Integer quantidade) {
    }


