package backend.tpservices.Services;

import backend.tpservices.Models.Client;
import backend.tpservices.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();

        clientRepository.findAll().forEach(clients::add); // dlhsi zapis je .foreach(client -> clients.add(client)) (brave new world)
        return clients;
    }
}
