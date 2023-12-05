package co.com.bussine.jpa.company.mapper;

import co.com.bussine.jpa.clients.ClientsDto;
import co.com.bussine.jpa.company.CompanyDto;
import co.com.bussine.model.clients.Clients;
import co.com.bussine.model.company.Company;

public class CompanyMapper {
    private CompanyMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Company companyDtoACompany(CompanyDto companyDto){
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .img(companyDto.getImg())
                .documentType(companyDto.getDocumentType())
                .identification(companyDto.getIdentification())
                .address(companyDto.getAddress())
                .phone(companyDto.getPhone())
                .city(companyDto.getCity())
                .email(companyDto.getEmail())
                .imposedName(companyDto.getImposedName())
                .quantity(companyDto.getQuantity())
                .money(companyDto.getMoney())
                .symbol(companyDto.getSymbol())
                .build();
    }

    public static CompanyDto companyACompanyDto(Company company){
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .img(company.getImg())
                .documentType(company.getDocumentType())
                .identification(company.getIdentification())
                .address(company.getAddress())
                .phone(company.getPhone())
                .city(company.getCity())
                .email(company.getEmail())
                .imposedName(company.getImposedName())
                .quantity(company.getQuantity())
                .money(company.getMoney())
                .symbol(company.getSymbol())
                .build();
    }
}
