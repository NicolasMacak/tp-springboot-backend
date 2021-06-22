package backend.tpservices.Repositories;

import backend.tpservices.Models.UserTypes.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {}