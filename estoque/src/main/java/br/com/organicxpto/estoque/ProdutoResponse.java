package br.com.organicxpto.estoque;

import java.math.BigDecimal;

public class ProdutoResponse {

    private Long idProduto;

    private BigDecimal quantidade;

    public ProdutoResponse() {
        super();
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

}
