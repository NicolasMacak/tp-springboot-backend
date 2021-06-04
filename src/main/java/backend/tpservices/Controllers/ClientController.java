package backend.tpservices.Controllers;
import backend.tpservices.Models.Client;
import backend.tpservices.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/client")
    private List<Client> getAllClients(){
        return clientService.getAllClients();
    }
}
