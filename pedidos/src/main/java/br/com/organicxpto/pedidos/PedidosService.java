package br.com.organicxpto.pedidos;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosService {

    private List<Pedido> pedidos = new ArrayList<>();

    private EstoquePedidoProducer estoquePedidoProducer;

    private PedidoRepository pedidoRepository;


    public PedidosService(EstoquePedidoProducer estoquePedidoProducer,PedidoRepository pedidoRepository) {
        this.estoquePedidoProducer = estoquePedidoProducer;
        this.pedidoRepository = pedidoRepository;

    }

    public List<Pedido> getAll(){

        return this.pedidoRepository.findAll();
        //return this.pedidos;
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
            this.pedidoRepository.save(pedido);
        }
        catch (Exception e)
        {
            throw new UnsupportedOperationException("Fora de Estoque");
        }

    }
}
