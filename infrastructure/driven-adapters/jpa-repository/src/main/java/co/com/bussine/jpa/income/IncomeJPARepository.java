package co.com.bussine.jpa.income;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeJPARepository extends JpaRepository<IncomeDto , String> ,QueryByExampleExecutor<IncomeDto> {
    @Query( "SELECT i.id, i.createAt, p.name, s.name, i.updateAt, i.series, i.total, i.status FROM IncomeDto i " +
            "LEFT JOIN PaymentTypeDto p ON i.paymentType.id = p.id " +
            "LEFT JOIN SupplierDto s ON i.supplier.id = s.id " +
            "WHERE i.search LIKE %:search%")
    List<IncomeDto> findBySearchLike(@Param("search") String search);

}
