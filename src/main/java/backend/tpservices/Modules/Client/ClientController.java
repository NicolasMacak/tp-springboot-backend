package backend.tpservices.Modules.Client;
import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
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
    private ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.getAllClients().orElseThrow(NoResultException::new);

        return clients.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
                                   new ResponseEntity<>(clients, HttpStatus.OK);

    }

    @GetMapping(value = "/{userId}")
    private ResponseEntity<Client> getClientById(@PathVariable final Long userId) throws NoSuchObjectException {
        Client client = clientService
                .getClientById(userId)
                .orElseThrow(() -> new NoSuchObjectException("Client with id = "+userId+" not found"));

        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<SuccessObject> addClient(@RequestBody Client client) throws InvalidObjectException {

        if(!client.getContact().isValid()) {
            // ak by sme chceli byt strasne fancy, mohli by sme dat do response ake fieldy su zle
            throw new InvalidObjectException("Invalid contact fields");
        }
        clientService.insertClientToDb(client);
        SuccessObject success = new SuccessObject(HttpStatus.OK,
                "User " + client.getContact().getFirstName()
                        + " " + client.getContact().getLastName()
                        + " successfully added");

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}")
    private ResponseEntity<SuccessObject> modifyClient(@PathVariable final Long userId,
                                                       @RequestBody Client client) throws InvalidObjectException, NoSuchObjectException {

        if(!client.getContact().isModifyValid()) throw new InvalidObjectException("Invalid contact fields");

        if (clientService.modifyClient(userId, client)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "User with id = " + userId + " successfully modified");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("User with id = " + userId + " not found");
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
