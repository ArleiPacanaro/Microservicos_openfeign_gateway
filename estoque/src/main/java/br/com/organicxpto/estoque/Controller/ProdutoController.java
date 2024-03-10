package br.com.organicxpto.estoque.Controller;

import br.com.organicxpto.estoque.Produto.Produto;
import br.com.organicxpto.estoque.Produto.Request.ProdutoRequest;
import br.com.organicxpto.estoque.Service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Produto>> getAllProdutos() {
        return ResponseEntity.ok(produtoService.getAll());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> criarProduto(@RequestBody ProdutoRequest produtoRequest) {
        try {
            Produto produtoCriado = produtoService.criarProduto(produtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar produto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/preco")
    public ResponseEntity<?> atualizarPreco(@PathVariable Long id, @RequestParam BigDecimal novoPreco) {
        Produto produtoEditado = produtoService.atualizarPreco(id, novoPreco);
        return ResponseEntity.status(HttpStatus.OK).body(produtoEditado);
    }

    @PutMapping("/{id}/quantidade")
    public ResponseEntity<?> atualizarQuantidade(@PathVariable Long id, @RequestParam int novaQuantidade) {

        Produto produtoEditado = produtoService.atualizarQuantidade(id, novaQuantidade);
        return ResponseEntity.status(HttpStatus.OK).body(produtoEditado);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

}
