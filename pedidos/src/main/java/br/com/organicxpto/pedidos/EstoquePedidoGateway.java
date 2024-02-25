package br.com.organicxpto.pedidos;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

// Esta classe vai substiruir a EstoquePedidoProducer que usei Feign
// Para utuliza-la preciso criar a COnfiguration para o Gateway que e a EstoquePedidoConfiguration
@MessagingGateway
public interface EstoquePedidoGateway {

    // Vamos informar o channel e o header, avisando que vamos recueprar algo no canal de resposta

    @Gateway(requestChannel = "estoque", requestTimeout = 5000
            , headers = @GatewayHeader(name = MessageHeaders.REPLY_CHANNEL
            , expression = "@nullChannel"))
    void removerEstoque(Message<RemoverEstoqueRequest> removerEstoqueRequestMessage);
}
