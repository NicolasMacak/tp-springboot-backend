package backend.tpservices.Modules.Courier;

import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("courier")
public class CourierController {
    @Autowired
    CourierService courierService;

    @GetMapping()
    private ResponseEntity<List<Courier>> getAllCouriers(){
        List<Courier> couriers = courierService.getAllCouriers().orElseThrow(NoResultException::new);

        return couriers.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
                new ResponseEntity<>(couriers, HttpStatus.OK);
    }

    @GetMapping(value = "/{courierId}")
    private ResponseEntity<Courier> getCourierById(@PathVariable final Long courierId) throws NoSuchObjectException {
        Courier courier = courierService
                .getCourierById(courierId)
                .orElseThrow(() -> new NoSuchObjectException("Courier with id = "+courierId+" not found"));

        return new ResponseEntity<>(courier, HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<SuccessObject> addCourier(@RequestBody Courier courier) throws InvalidObjectException {

        if(!courier.getContact().isValid()) {
           throw new InvalidObjectException("Invalid contact fields");
        }
        courierService.insertCourierToDb(courier);
        SuccessObject success = new SuccessObject(HttpStatus.OK,
                "Courier " + courier.getContact().getFirstName()
                        + " " + courier.getContact().getLastName()
                        + " successfully added");

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping(value = "/{courierId}")
    private ResponseEntity<SuccessObject> modifyCourier(@PathVariable final Long courierId,
                                                        @RequestBody Courier courier) throws InvalidObjectException, NoSuchObjectException {

        if(!courier.getContact().isModifyValid()) throw new InvalidObjectException("Invalid contact fields");

        if (courierService.modifyCourier(courierId, courier)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "Courier with id = " + courierId + " successfully modified");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Courier with id = " + courierId + " not found");
    }

    @DeleteMapping(value = "/{courierId}")
    private ResponseEntity<SuccessObject> deleteCourier(@PathVariable final Long courierId) throws NoSuchObjectException {
        if(courierService.deleteCourier(courierId)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "Courier with id = " + courierId + " successfully deleted");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Courier with id = "+courierId+" not found");
    }
}
