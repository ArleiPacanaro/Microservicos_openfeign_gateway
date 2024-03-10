package br.com.organicxpto.estoque.Controller;



import br.com.organicxpto.estoque.Produto.Request.CompraRequest;
import br.com.organicxpto.estoque.Service.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ProdutoConsumer {

    private final ProdutoService produtoService;

    public ProdutoConsumer(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    //Objeto que vamos transacionar, preciso deixar com um Bean
    @Bean(name="remova-estoque")
    Consumer<CompraRequest> consumer(){

        return compraRequest -> this.produtoService.removerEstoque(
                compraRequest.getIdProduto(),
                compraRequest.getQuantidade()
        );
    }

}
