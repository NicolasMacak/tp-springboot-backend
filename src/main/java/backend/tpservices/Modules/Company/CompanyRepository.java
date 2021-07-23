package backend.tpservices.Modules.Company;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long>,
                                           JpaSpecificationExecutor<Company> {}