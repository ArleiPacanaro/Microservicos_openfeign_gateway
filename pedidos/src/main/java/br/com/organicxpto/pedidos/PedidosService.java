package br.com.organicxpto.pedidos;

import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidosService {

    private List<Pedido> pedidos = new ArrayList<>();

    private EstoquePedidoProducer estoquePedidoProducer; // vou deixar para fins de conhecimentos, porém não será mais o feign e sim o integration

    // Agora novo e ultimo padrão usando Spring Integration ao inves do Feign mas posso usar o RestTemplate
    private EstoquePedidoGateway estoquePedidoGateway;

    private PedidoRepository pedidoRepository;


    public PedidosService(EstoquePedidoProducer estoquePedidoProducer
            ,PedidoRepository pedidoRepository
            ,EstoquePedidoGateway estoquePedidoGateway) {
        this.estoquePedidoProducer = estoquePedidoProducer; // Posso tirar se quiser
        this.pedidoRepository = pedidoRepository;
        this.estoquePedidoGateway =estoquePedidoGateway;

    }

    public List<Pedido> getAll(){

        return this.pedidoRepository.findAll();
        //return this.pedidos;
    }


    public void save(Pedido pedido) {

        // Aqui validar com outro sistema
        // um pedido tem varios itens
        try {
            // Padrão usando o feign na estoque uso Spring Cloud Function

            /*
            pedido.getItens().forEach(p ->
                    this.estoquePedidoProducer.removerEstoque(
                            new RemoverEstoqueRequest(p.getIdProduct(), p.getQuantidade()))

            );

             */

            // Agora chamando com integration e ebom usar este GenericMessage

            pedido.getItens().forEach(p ->
                    this.estoquePedidoGateway.removerEstoque(
                            new GenericMessage<>(new RemoverEstoqueRequest(p.getIdProduct()
                                    , p.getQuantidade())))

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
