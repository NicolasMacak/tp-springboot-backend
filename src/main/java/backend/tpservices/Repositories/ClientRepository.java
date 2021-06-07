package backend.tpservices.Repositories;

import backend.tpservices.Models.UserTypes.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
/*
    List<Client> findByLastName(String lastName); // je mozne inicializovat taketo funkcie. staci dat findByNazovStlpca

    Client findByFirstName(String firstname);

    Client findById(long id);*/
}
