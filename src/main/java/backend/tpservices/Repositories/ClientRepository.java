package backend.tpservices.Repositories;

import backend.tpservices.Models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
/*
    List<Client> findByLastName(String lastName); // je mozne inicializovat taketo funkcie. staci dat findByNazovStlpca

    Client findByFirstName(String firstname);

    Client findById(long id);*/
}
