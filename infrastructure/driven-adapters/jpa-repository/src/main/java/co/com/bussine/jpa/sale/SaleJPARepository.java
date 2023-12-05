package co.com.bussine.jpa.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleJPARepository extends JpaRepository<SaleDto,String> , QueryByExampleExecutor<SaleDto> {
}
