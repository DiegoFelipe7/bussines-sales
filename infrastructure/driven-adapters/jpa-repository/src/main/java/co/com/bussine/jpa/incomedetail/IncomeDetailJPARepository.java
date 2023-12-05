package co.com.bussine.jpa.incomedetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IncomeDetailJPARepository extends JpaRepository<IncomeDetailDto, String>, QueryByExampleExecutor<IncomeDetailDto> {

    Optional<IncomeDetailDto> findByIncomeIdAndProductsId(String id, String productId);
    Optional<IncomeDetailDto> findByIncomeId(String id);

}
