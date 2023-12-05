package co.com.bussine.api.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ClientsRouterRest {
    private static final String API_PATH = "api/";
    @Bean
    public RouterFunction<ServerResponse>routerFunctionClients(ClientsHandler clientsHandler){
        return RouterFunctions.route()
                .GET(API_PATH+"clients/list", clientsHandler::getAllClients)
                .GET(API_PATH+"clients/{identification}",clientsHandler::getIdClients)
                .POST(API_PATH+"client" , clientsHandler::createClient)
                .PUT(API_PATH+"client-update/{id}" , clientsHandler::updateClient)
                .build();
    }
}
