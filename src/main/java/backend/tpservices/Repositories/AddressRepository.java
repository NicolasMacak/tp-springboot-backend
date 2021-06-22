package backend.tpservices.Repositories;

import backend.tpservices.Models.Embedded.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {}
