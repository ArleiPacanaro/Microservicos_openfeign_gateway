package br.com.organicxpto.estoque;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    private List<Produto> produtos =
            List.of(
                    new Produto(1L,"Tomate",  BigDecimal.valueOf(100000)),
                    new Produto(2L,"Abacaxi", BigDecimal.TEN)

            );

    public List<Produto> getAll(){
        return produtos;
    }

    public void removerEstoque(Long idProduct, BigDecimal quantidade){

        this.produtos.stream().filter(p -> p.getId().equals(idProduct)).findFirst()
                .orElseThrow().removerEstoque(quantidade);


    }
}
