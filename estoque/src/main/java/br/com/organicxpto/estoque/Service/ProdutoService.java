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

    public Produto criarProduto(ProdutoRequest produtoRequest) {
        Produto produto = new Produto();
        produto.setNome(produtoRequest.nome());
        produto.setDescricao(produtoRequest.descricao());
        produto.setPreco(produtoRequest.preco());
        produto.setQuantidade(produtoRequest.quantidade());
        produto.setDataCriacao(LocalDateTime.now());

        return produtoRepository.save(produto);
    }

    public Produto atualizarPreco(Long id, BigDecimal novoPreco) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        produto.setPreco(novoPreco);
        return produtoRepository.save(produto);
    }

    public Produto atualizarQuantidade(Long id, int quantidadeParaAdicionar) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (quantidadeParaAdicionar < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser menor que zero");
        }

        int novaQuantidade = produto.getQuantidade() + quantidadeParaAdicionar;

        produto.setQuantidade(novaQuantidade);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (produto.getQuantidade() != 0) {
            throw new IllegalStateException("Não é possível excluir o produto pois a quantidade não está zerada.");
        }

        produtoRepository.delete(produto);
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
