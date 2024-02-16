package br.com.organicxpto.estoque;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Produto>> getAllProdutos()
    {
        return ResponseEntity.ok(produtoService.getAll());
    }
}
