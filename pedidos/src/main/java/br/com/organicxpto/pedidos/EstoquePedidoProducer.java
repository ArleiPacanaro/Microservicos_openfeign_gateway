package br.com.organicxpto.pedidos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

// posso tirar esta classe ja que estou usando o spring integration ao inves do Feign, mas vou deixar para conhecimento
@FeignClient(value = "estoque", url= "http://localhost:8081/api")
public interface EstoquePedidoProducer {

    // irá consumir o microserviço
    @PostMapping(value = "/consumer-remova-estoque")
    void removerEstoque(RemoverEstoqueRequest removerEstoqueRequest);
}
