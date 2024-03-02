package br.com.organicxpto.estoque.Service;

import br.com.organicxpto.estoque.Produto.Produto;
import br.com.organicxpto.estoque.Produto.Request.ProdutoRequest;
import br.com.organicxpto.estoque.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> getAll() {
        return produtoRepository.findAll();
    }

    public void criarProduto(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setDescricao(produtoRequest.descricao());
        produto.setPreco(produtoRequest.preco());
        produto.setQuantidade(produtoRequest.quantidade());
        produto.setDataCriacao(LocalDateTime.now());

        produtoRepository.save(produto);
    }

    public void removerEstoque(Long idProduto, Integer quantidade) {
//
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(RuntimeException::new);
        produto.removerEstoque(quantidade);
//        produtoRepository.save(produto);

//        this.produtos.stream().filter(
//                        p -> p.getId().equals(idProduct)).findFirst()
//                .orElseThrow().removerEstoque(quantidade);

    }
}
