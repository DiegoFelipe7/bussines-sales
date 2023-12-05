package co.com.bussine.api.clients;

import co.com.bussine.api.utils.Pagination;
//import co.com.bussine.api.validation.ObjectValidator;
import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import co.com.bussine.usecase.clients.createclient.CreateClientUseCase;
import co.com.bussine.usecase.clients.getallclients.GetAllClientsUseCase;
import co.com.bussine.usecase.clients.getidclient.GetIdClientUseCase;
import co.com.bussine.usecase.clients.updateclient.UpdateClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientsHandler {
    private final GetAllClientsUseCase getAllClientsUseCase;
    private final GetIdClientUseCase getIdClientUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    public Mono<ServerResponse> getAllClients(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getAllClientsUseCase.apply(Pagination.pagination(serverRequest)), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> getIdClients(ServerRequest serverRequest) {
        String identification = serverRequest.pathVariable("identification");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(getIdClientUseCase.apply(identification), ResponseShopSavvyDTO.class);
    }

    public Mono<ServerResponse> createClient(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Clients.class)
               // .doOnNext(objectValidator::validate)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(createClientUseCase.apply(ele), ResponseShopSavvy.class));

    }
    public Mono<ServerResponse> updateClient(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return serverRequest.bodyToMono(Clients.class)
                .flatMap(ele -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(updateClientUseCase.apply(id,ele), ResponseShopSavvy.class));

    }
}
