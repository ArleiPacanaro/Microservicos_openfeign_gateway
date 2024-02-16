package br.com.organicxpto.estoque;

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
    Consumer<ProdutoRequested> consumer(){

        return produtoRequested -> this.produtoService.removerEstoque(
                produtoRequested.getIdProduto(),
                produtoRequested.getQuantidade()
        );
    }

}
