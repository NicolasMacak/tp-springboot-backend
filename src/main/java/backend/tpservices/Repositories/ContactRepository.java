package backend.tpservices.Repositories;

import backend.tpservices.Models.Embedded.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
