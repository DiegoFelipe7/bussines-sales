package co.com.bussine.jpa.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJPARepository  extends JpaRepository<CompanyDto, String> {
}
