package co.com.bussine.usecase.clients.createclient;

import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.clients.gateways.ClientsRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateClientUseCase implements Function<Clients , Mono<ResponseShopSavvy<Clients>>> {
    private final ClientsRepository clientsRepository;
    @Override
    public Mono<ResponseShopSavvy<Clients>> apply(Clients clients) {
        return clientsRepository.createClient(clients);
    }
}
