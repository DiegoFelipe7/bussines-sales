package co.com.bussine.jpa.paymenttype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentTypeJPARepository extends JpaRepository<PaymentTypeDto, String> , QueryByExampleExecutor<PaymentTypeDto> {
    @Query("SELECT p FROM PaymentTypeDto p WHERE p.search LIKE %:search%")
    List<PaymentTypeDto> findAllBySearchLike(@Param("search") String search);

}
