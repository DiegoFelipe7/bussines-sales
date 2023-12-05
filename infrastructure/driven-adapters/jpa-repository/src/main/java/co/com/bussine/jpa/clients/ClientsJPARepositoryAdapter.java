package co.com.bussine.jpa.clients;

import co.com.bussine.jpa.clients.mapper.ClientsMapper;
import co.com.bussine.jpa.helper.AdapterOperations;
import co.com.bussine.jpa.service.EmailService;
import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.clients.gateways.ClientsRepository;
import co.com.bussine.model.common.BusinessException;
import co.com.bussine.model.utils.PaginationDTO;
import co.com.bussine.model.utils.ResponseShopSavvy;
import co.com.bussine.model.utils.ResponseShopSavvyDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class ClientsJPARepositoryAdapter extends AdapterOperations<Clients, ClientsDto, String, ClientsJPARepository>
        implements ClientsRepository {
    private final EmailService emailService;

    public ClientsJPARepositoryAdapter(ClientsJPARepository repository, ObjectMapper mapper, EmailService emailService) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Clients.ClientsBuilder.class).build());
        this.emailService = emailService;
    }

    @Override
    public Mono<ResponseShopSavvyDTO<List<Clients>>> getAllClients(PaginationDTO paginationDTO) {
        return Flux.fromIterable(repository.findAllBySearchLike(paginationDTO.getSearch()))
                .map(ClientsMapper::clientsDtoAClients)
                .skip(paginationDTO.getOffset())
                .take(paginationDTO.getLimit())
                .collectList()
                .zipWith(Mono.just(repository.count()))
                .map(ele -> new ResponseShopSavvyDTO<List<Clients>>(ele.getT1(), paginationDTO.pagination(ele.getT2())));
    }

    @Override
    public Mono<ResponseShopSavvy<Clients>> getIdClient(String identification) {
        return repository.findByIdentification(identification)
                .map(ele -> new ResponseShopSavvy<Clients>(ClientsMapper.clientsDtoAClients(ele)))
                .map(Mono::just)
                .orElse(Mono.empty());

    }

    @Override
    public Mono<ResponseShopSavvy<Clients>> createClient(Clients clients) {
        if (clients.getEmail() == null) {
            return saveClientWithoutEmail(clients);
        }
        if (repository.findByEmailIgnoreCaseOrIdentification(clients.getEmail(), clients.getIdentification()).isPresent()) {
            return Mono.error(new BusinessException(BusinessException.Type.ERROR_USUARIO_EXISTE));
        }
        return saveAndSendWelcomeEmail(clients);
    }

    private Mono<ResponseShopSavvy<Clients>> saveClientWithoutEmail(Clients clients) {
        ClientsDto clientsDto = repository.save(ClientsMapper.clientsAClientsDto(clients));
        return Mono.just(new ResponseShopSavvy<>(ClientsMapper.clientsDtoAClients(clientsDto)));
    }

    private Mono<ResponseShopSavvy<Clients>> saveAndSendWelcomeEmail(Clients clients) {
        ClientsDto clientsDto = repository.save(ClientsMapper.clientsAClientsDto(clients));
        if (clients.getEmail() != null) {
            return emailService.sendEmailWelcome(clientsDto.getName(), clients.getEmail())
                    .thenReturn(new ResponseShopSavvy<>(ClientsMapper.clientsDtoAClients(clientsDto)));
        }

        return Mono.just(new ResponseShopSavvy<>(ClientsMapper.clientsDtoAClients(clientsDto)));
    }

    @Override
    public Mono<ResponseShopSavvy<Clients>> updateClient(String id, Clients clients) {
        return repository.findById(id)
                .map(ele -> {
                    ele.setId(id);
                    ClientsDto clientsDto = repository.save(ClientsMapper.clientsAClientsDto(clients));
                    return new ResponseShopSavvy<Clients>(ClientsMapper.clientsDtoAClients(clientsDto));
                })
                .map(Mono::just)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.ERROR_USUARIO_EXISTE));
    }

    @Override
    public Mono<Long> countClients() {
        return Mono.just(repository.count());
    }
}
