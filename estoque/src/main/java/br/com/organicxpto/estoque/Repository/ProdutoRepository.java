package br.com.organicxpto.estoque.Repository;

import br.com.organicxpto.estoque.Produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}