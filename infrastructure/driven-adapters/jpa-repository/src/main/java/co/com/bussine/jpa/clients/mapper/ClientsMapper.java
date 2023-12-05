package co.com.bussine.jpa.clients.mapper;

import co.com.bussine.jpa.clients.ClientsDto;
import co.com.bussine.model.clients.Clients;

public class ClientsMapper {
    private ClientsMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Clients clientsDtoAClients(ClientsDto clientsDto){
        return Clients.builder()
                .id(clientsDto.getId())
                .name(clientsDto.getName())
                .lastName(clientsDto.getLastName())
                .email(clientsDto.getEmail())
                .documentType(clientsDto.getDocumentType())
                .identification(clientsDto.getIdentification())
                .address(clientsDto.getAddress())
                .phone(clientsDto.getPhone())
                .search(clientsDto.concatText())
                .createAt(clientsDto.getCreateAt())
                .updateAt(clientsDto.getUpdateAt())
                .build();
    }


    public static ClientsDto clientsAClientsDto(Clients clients){
        return ClientsDto.builder()
                .id(clients.getId())
                .name(clients.getName())
                .lastName(clients.getLastName())
                .email(clients.getEmail())
                .documentType(clients.getDocumentType())
                .identification(clients.getIdentification())
                .address(clients.getAddress())
                .phone(clients.getPhone())
                .search(clients.concatText())
                .createAt(clients.getCreateAt())
                .updateAt(clients.getUpdateAt())
                .build();
    }
}
