package br.com.organicxpto.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//Pois vai ter Beans
public class ConfigGateway {

    @Bean
    public RouteLocator custom(RouteLocatorBuilder builder){
        // no not  fica o serviço que queremos proteger...
        // o Gateway esconde os microserviços e o gateway que os acessa
        // o Gateway que deve estar exposto na rede publica

        return builder.routes()
                .route( "estoque", r -> r.path("/estoque/**")
                                .and()
                                .not(p -> p.path("/estoque/api/**"))
                                .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081"))
                .route( "pedidos", r -> r.path("/pedidos/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082"))
                .build();
    }

}
