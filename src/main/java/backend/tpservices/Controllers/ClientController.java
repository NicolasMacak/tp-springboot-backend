package backend.tpservices.Controllers;
import backend.tpservices.Models.Embedded.Contact;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Services.ClientService;
import backend.tpservices.TpServicesApplication;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("client")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(TpServicesApplication.class);
    @Autowired
    ClientService clientService;

    @GetMapping()
    private List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping()
    private ResponseEntity<String> addClient(@RequestBody Client client){

        if(client.getContact().isInvalid()) {
            return new ResponseEntity<String>("Invalid contact fields", HttpStatus.BAD_REQUEST);
        }

        clientService.insertClientToDb(client);
        return new ResponseEntity<String>("User added", HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<String> modifyClient(@RequestBody Client client){

        if(client.getContact().isInvalid()) {
            return new ResponseEntity<String>("Invalid contact fields", HttpStatus.BAD_REQUEST);
        }

        clientService.modifyClient(client);
        return new ResponseEntity<String>("User modified", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    private ResponseEntity<String> deleteClient(@PathVariable final Long userId){
        if(clientService.deleteClient(userId)){
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        }

        return new ResponseEntity<String>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);// toto sa mi nelubi. chcem posielat oznamy zo service. Nech viem povedat presne co sa pokazilo
    }
}
