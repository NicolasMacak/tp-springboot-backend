package backend.tpservices.Modules.Courier;

import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import backend.tpservices.Modules.Review.CourierReview;
import backend.tpservices.Modules.Review.Review;
import backend.tpservices.Modules.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("courier")
public class CourierController {
    @Autowired
    CourierService courierService;
    @Autowired
    ReviewService reviewService;

    @GetMapping()
    private ResponseEntity<List<Courier>> getAllCouriers(){
        List<Courier> couriers = courierService.getAllCouriers().orElseThrow(NoResultException::new);
        return new ResponseEntity<>(couriers, HttpStatus.OK);
    }

    @GetMapping(value = "/{courierId}")
    private ResponseEntity<Courier> getCourierById(@PathVariable final Long courierId) throws NoSuchObjectException {
        Courier courier = courierService
                .getCourierById(courierId)
                .orElseThrow(() -> new NoSuchObjectException("Courier with id = "+courierId+" not found"));

        return new ResponseEntity<>(courier, HttpStatus.OK);
    }

    @GetMapping("/{courierId}/review")
    private ResponseEntity<List<Review>> getReviewsById(@PathVariable("courierId") Long courierId) throws NoSuchObjectException {
        Optional<Courier> optCourier = courierService.getCourierById(courierId);
        if (optCourier.isPresent()) {
            List<Review> reviews = optCourier.get().getReviewList();
            if(reviews.isEmpty()) throw new NoResultException();
            return new ResponseEntity<>(reviews,HttpStatus.OK);
        }
        throw new NoSuchObjectException("Courier with courierId = " + courierId + " not found");
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

    @PostMapping("/{courierId}/review")
    private ResponseEntity<SuccessObject> addReview(@PathVariable("courierId") Long courierId,
                                                    @RequestBody CourierReview review) throws InvalidObjectException, NoSuchObjectException {

        if(!review.isValid()) throw new InvalidObjectException("Invalid review fields");

        if (reviewService.addCourierReview(courierId,review)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK, "Review successfully added");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Courier with courierId = "+ courierId +" not found");
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
