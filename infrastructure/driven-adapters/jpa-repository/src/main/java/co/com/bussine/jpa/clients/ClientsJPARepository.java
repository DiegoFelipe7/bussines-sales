package co.com.bussine.jpa.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientsJPARepository extends JpaRepository<ClientsDto,String>, QueryByExampleExecutor<ClientsDto> {

    @Query("SELECT c FROM ClientsDto c WHERE c.search LIKE %:search%")
    List<ClientsDto> findAllBySearchLike(@Param("search") String search);
    Optional<ClientsDto> findByEmailIgnoreCaseOrIdentification(String email,String identification);
    Optional<ClientsDto> findByIdentification(String identification);
}
