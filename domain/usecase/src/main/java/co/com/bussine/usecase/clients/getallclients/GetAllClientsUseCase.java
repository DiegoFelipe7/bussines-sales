package co.com.bussine.usecase.clients.getallclients;

import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.clients.gateways.ClientsRepository;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class GetAllClientsUseCase implements Function<PaginationDTO , Mono<ResponseShopSavvyDTO<List<Clients>>>> {
    private final ClientsRepository clientsRepository;
    @Override
    public Mono<ResponseShopSavvyDTO<List<Clients>>> apply(PaginationDTO paginationDTO) {
        return clientsRepository.getAllClients(paginationDTO);
    }
}
