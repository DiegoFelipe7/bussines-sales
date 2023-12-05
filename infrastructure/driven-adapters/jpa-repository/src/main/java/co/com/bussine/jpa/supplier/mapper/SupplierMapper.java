package co.com.bussine.jpa.supplier.mapper;
import co.com.bussine.jpa.supplier.SupplierDto;
import co.com.bussine.model.supplier.Supplier;

public class SupplierMapper {
    private SupplierMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Supplier supplierDtoASupplier(SupplierDto supplierDto){
        return Supplier.builder()
                .id(supplierDto.getId())
                .name(supplierDto.getName())
                .email(supplierDto.getEmail())
                .documentType(supplierDto.getDocumentType())
                .identification(supplierDto.getIdentification())
                .address(supplierDto.getAddress())
                .phone(supplierDto.getPhone())
                .search(supplierDto.getSearch())
                .status(supplierDto.getStatus())
                .createAt(supplierDto.getCreateAt())
                .updateAt(supplierDto.getUpdateAt())
                .build();
    }

    public static SupplierDto supplierASupplierDto(Supplier supplier){
        return SupplierDto.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .email(supplier.getEmail())
                .documentType(supplier.getDocumentType())
                .identification(supplier.getIdentification())
                .address(supplier.getAddress())
                .phone(supplier.getPhone())
                .search(supplier.concatText())
                .status(supplier.getStatus())
                .createAt(supplier.getCreateAt())
                .updateAt(supplier.getUpdateAt())
                .build();
    }

}
