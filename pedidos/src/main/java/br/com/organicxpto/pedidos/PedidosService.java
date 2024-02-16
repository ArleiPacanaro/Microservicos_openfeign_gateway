package br.com.organicxpto.pedidos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosService {

    private List<Pedido> pedidos = new ArrayList<>();

    private EstoquePedidoProducer estoquePedidoProducer;

    public PedidosService(EstoquePedidoProducer estoquePedidoProducer) {
        this.estoquePedidoProducer = estoquePedidoProducer;
    }

    public List<Pedido> getAll(){
        return this.pedidos;
    }


    public void save(Pedido pedido) {

        // Aqui validar com outro sistema
        // um pedido tem varios itens
        try {

            pedido.getItens().forEach(p ->
                    this.estoquePedidoProducer.removerEstoque(
                            new RemoverEstoqueRequest(p.getIdProduct(), p.getQuantidade()))

            );

            this.pedidos.add(pedido);
        }
        catch (Exception e)
        {
            throw new UnsupportedOperationException("Fora de Estoque");
        }

    }
}
