package backend.tpservices.Services;

import backend.tpservices.Models.Embedded.Contact;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Repositories.ClientRepository;
import backend.tpservices.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();

        clientRepository.findAll().forEach(clients::add); // dlhsi zapis je .foreach(client -> clients.add(client)) (brave new world)
        return clients;
    }

    // tutok treba v ďalšom patchi transakcie, lebo tak sa to robí.
    // .save obsahuje transackiu. Zbytočne vytvárame 2 transakcie keď stačí jedna.
    // also, možno potom nejako nebudeme musieť realizovať contactRepository.
    public void insertClientToDb(Client client){
      /*  Client client = new Client();
        client.setContact(client);*/

        clientRepository.save(client);
    }

    public void modifyClient(Client client){ // index contactu sa updatuje. asi si myslí že je to nový record či čo
        Optional<Client> optionalClient = clientRepository.findById(client.getId());
        if(optionalClient.isEmpty()){ return; }

        optionalClient.get().setContact(client.getContact());
        clientRepository.save(optionalClient.get());
    }

    public boolean deleteClient(Long id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if(optionalClient.isEmpty()){ return false; }

        clientRepository.delete(optionalClient.get());
        return true;
    }
}
