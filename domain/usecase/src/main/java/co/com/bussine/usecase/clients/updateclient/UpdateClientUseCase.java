package co.com.bussine.usecase.clients.updateclient;

import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.clients.gateways.ClientsRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateClientUseCase implements BiFunction<String,Clients, Mono<ResponseShopSavvy<Clients>>> {
    private final ClientsRepository clientsRepository;
    @Override
    public Mono<ResponseShopSavvy<Clients>> apply(String id, Clients clients) {
        return clientsRepository.updateClient(id, clients);
    }
}
