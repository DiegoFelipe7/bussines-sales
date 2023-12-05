package co.com.bussine.jpa.supplier;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SupplierJPARepository extends JpaRepository<SupplierDto,String>, QueryByExampleExecutor<SupplierDto> {

    @Query("SELECT s FROM SupplierDto s WHERE s.search LIKE %:search%")
    List<SupplierDto> findAllBySearchLike(@Param("search") String search);
    Optional<SupplierDto> findByIdentification(String identification);
}
