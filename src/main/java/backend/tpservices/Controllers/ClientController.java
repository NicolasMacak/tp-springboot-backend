package backend.tpservices.Controllers;
import backend.tpservices.Models.Embedded.Contact;
import backend.tpservices.Models.General.SuccessObject;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Services.ClientService;
import backend.tpservices.TpServicesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
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
        // tuto mozeme a nemusime hodit error
        // bud vratime prazdny list [] alebo vratime 204 No Content a nevrati sa vobec nic
        return clientService.getAllClients().orElseThrow(NoResultException::new);
    }

    @GetMapping(value = "/{userId}")
    private Client getClientById(@PathVariable final Long userId) throws NoSuchObjectException {
        return clientService
                .getClientById(userId)
                .orElseThrow(() -> new NoSuchObjectException("Client with id = "+userId+" not found"));
    }

    @PostMapping()
    private ResponseEntity<SuccessObject> addClient(@RequestBody Contact contact) throws InvalidObjectException {
        if(contact.isInvalid()) {
            // ak by sme chceli byt strasne fancy, mohli by sme dat do response ake fieldy su zle
            throw new InvalidObjectException("Invalid contact fields");
        }
        clientService.insertClientToDb(contact);
        SuccessObject success = new SuccessObject(HttpStatus.OK,
                "User " + client.getContact().getFirstName()
                        + " " + client.getContact().getLastName()
                        + " successfully added");

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    private ResponseEntity<SuccessObject> deleteClient(@PathVariable final Long userId) throws NoSuchObjectException {
        if(clientService.deleteClient(userId)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "User with id = " + userId + " successfully deleted");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Client with id = "+userId+" not found");
    }
}
