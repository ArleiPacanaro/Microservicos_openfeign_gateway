package br.com.organicxpto.pedidos;

import ch.qos.logback.core.util.FixedDelay;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
// Ver a diferenção para Configuration que ai usamos Beans
public class GeradorPedidos {

    private final PedidosService pedidosService;

    public GeradorPedidos(PedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @Scheduled(fixedDelay = 10,timeUnit = TimeUnit.SECONDS)
    public void gerarPedidos(){

        this.pedidosService.save( new Pedido( UUID.randomUUID(),
                                              UUID.randomUUID(),
                List.of(new Item(1L, BigDecimal.TEN))));
    }
}
