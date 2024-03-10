package br.com.organicxpto.estoque.Controller;

import br.com.organicxpto.estoque.Produto.Produto;
import br.com.organicxpto.estoque.Produto.Request.ProdutoRequest;
import br.com.organicxpto.estoque.Service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> criarProduto(@RequestBody  ProdutoRequest produtoRequest) {
        try {
            produtoService.criarProduto(produtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar produto: " + e.getMessage());
        }
    }
}
