package co.com.bussine.usecase.clients.getidclient;

import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.clients.gateways.ClientsRepository;
import co.com.bussine.model.utils.ResponseShopSavvy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetIdClientUseCase implements Function<String, Mono<ResponseShopSavvy<Clients>>> {
    private final ClientsRepository clientsRepository;
    @Override
    public Mono<ResponseShopSavvy<Clients>> apply(String identification) {
        return clientsRepository.getIdClient(identification);
    }
}
