package co.com.bussine.model.clients.gateways;

import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ClientsRepository {
    Mono<ResponseShopSavvyDTO<List<Clients>>> getAllClients(PaginationDTO paginationDTO);
    Mono<ResponseShopSavvy<Clients>> getIdClient(String identification);

    Mono<ResponseShopSavvy<Clients>> createClient(Clients clients);
    Mono<ResponseShopSavvy<Clients>> updateClient(String id,Clients clients);
    Mono<Long> countClients();

}
