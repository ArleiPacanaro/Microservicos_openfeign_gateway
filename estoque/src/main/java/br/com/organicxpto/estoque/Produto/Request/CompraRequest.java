package br.com.organicxpto.estoque.Produto.Request;

public class CompraRequest {
    private Long idProduto;

    private Integer quantidade;

    public CompraRequest() {
        super();
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
