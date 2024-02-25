package br.com.organicxpto.pedidos;

import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;

// Tem que criar um channel para a mensagem trafegar e um fluxo
@Configuration
public class EstoquePedidoConfiguration {

    //usando o mesmo nome do Gateway mas poderia ser diferente ajustando o name do bean
    @Bean
    public MessageChannel estoque(){
        DirectChannel directChannel = new DirectChannel();
        directChannel.setFailover(false);
        return directChannel;

    }

    // Vamos usar http, mas podemos aqui usar s3, kinesis, ftp e etc, temos que adionar no POM a depe do tipo de comunicação...
    @Bean
    public IntegrationFlow estoqueFlow(){

        return IntegrationFlow.from("estoque")
                .handle(Http.outboundGateway("http://localhost:8081/api")
                        .httpMethod(HttpMethod.POST))
                        .log()
                        .bridge().get();

    }

}
