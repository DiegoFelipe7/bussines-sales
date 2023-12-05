package co.com.bussine.jpa.income.mapper;

import co.com.bussine.jpa.income.IncomeDto;
import co.com.bussine.jpa.incomedetail.mapper.IncomeDetailMapper;
import co.com.bussine.jpa.paymenttype.mapper.PaymentTypeMapper;
import co.com.bussine.jpa.supplier.mapper.SupplierMapper;
import co.com.bussine.model.income.Income;
import co.com.bussine.model.income.Incomes;

public class IncomeMapper {
    private IncomeMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Income incomeDtoAIncome(IncomeDto incomeDto) {
        return Income.builder()
                .id(incomeDto.getId())
                .supplier(SupplierMapper.supplierDtoASupplier(incomeDto.getSupplier()))
                .paymentType(PaymentTypeMapper.paymentTypeDtoAPayment(incomeDto.getPaymentType()))
                .createAt(incomeDto.getCreateAt())
                .series(incomeDto.getSeries())
                .voucherNumber(incomeDto.getVoucherNumber())
                .taxes(incomeDto.getTaxes())
                .total(incomeDto.getTotal())
                .status(incomeDto.getStatus())
                .search(incomeDto.concatText())
                //.users(User incomeDto.getUsers())
                .updateAt(incomeDto.getUpdateAt())
                .build();
    }

    public static Incomes incomeDtoAIncomes(IncomeDto incomeDto) {
        return Incomes.builder()
                .id(incomeDto.getId())
                .supplier(SupplierMapper.supplierDtoASupplier(incomeDto.getSupplier()))
                .paymentType(PaymentTypeMapper.paymentTypeDtoAPayment(incomeDto.getPaymentType()))
                .createAt(incomeDto.getCreateAt())
                .series(incomeDto.getSeries())
                .voucherNumber(incomeDto.getVoucherNumber())
                .taxes(incomeDto.getTaxes())
                .incomeDetails(incomeDto.getIncomeDetail().stream().map(IncomeDetailMapper::incomeDetailDtoAIncomeDetail).toList())
                .total(incomeDto.getTotal())
                .status(incomeDto.getStatus())
                .search(incomeDto.concatText())
                //.users(User incomeDto.getUsers())
                .updateAt(incomeDto.getUpdateAt())
                .build();
    }

    public static IncomeDto incomeAIncomeDto(Income income) {
        return IncomeDto.builder()
                .id(income.getId())
                .supplier(SupplierMapper.supplierASupplierDto(income.getSupplier()))
                .paymentType(PaymentTypeMapper.paymentTypeAPaymentDto(income.getPaymentType()))
                .createAt(income.getCreateAt())
                .series(income.getSeries())
                .voucherNumber(income.getVoucherNumber())
                .taxes(income.getTaxes())
                .total(income.getTotal())
                // .users(User incomeDto.getUsers())
                .status(income.getStatus())
                .search(income.getSearch())
                .updateAt(income.getUpdateAt())
                .build();

    }
}
