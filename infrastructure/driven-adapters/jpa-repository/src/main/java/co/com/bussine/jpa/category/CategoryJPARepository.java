package co.com.bussine.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryJPARepository extends JpaRepository<CategoryDto, String>, QueryByExampleExecutor<CategoryDto> {

    @Query("SELECT c FROM CategoryDto c WHERE c.search LIKE %:search%")
    List<CategoryDto> findAllBySearchLike(@Param("search") String search);

}
