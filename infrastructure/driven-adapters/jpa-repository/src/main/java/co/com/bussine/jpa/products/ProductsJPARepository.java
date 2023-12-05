package co.com.bussine.jpa.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsJPARepository extends JpaRepository<ProductsDto,String>, QueryByExampleExecutor<ProductsDto> {

    @Query("SELECT p FROM ProductsDto p WHERE p.search LIKE %:search%")
    List<ProductsDto> findAllBySearchLike(@Param("search") String search);

    @Query("SELECT p FROM ProductsDto p WHERE p.stock <=:stock")
    List<ProductsDto> findAllByStock(@Param("stock") Integer stock);

    Optional<ProductsDto> findByCode(String code);
}
