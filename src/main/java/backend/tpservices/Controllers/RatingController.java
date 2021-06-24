package backend.tpservices.Controllers;

import backend.tpservices.Models.General.SuccessObject;
import backend.tpservices.Models.Rating;
import backend.tpservices.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping()
    private ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> ratings = ratingService.getAllRatings().orElseThrow(NoResultException::new);

        return ratings.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
                new ResponseEntity<>(ratings, HttpStatus.OK);

    }

    @GetMapping(value = "/{ratingId}")
    private ResponseEntity<Rating> getRatingById(@PathVariable final Long ratingId) throws NoSuchObjectException {
        Rating rating = ratingService
                .getRatingById(ratingId)
                .orElseThrow(() -> new NoSuchObjectException("Rating with id = "+ratingId+" not found"));

        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Rating> addRating(@RequestBody Rating rating) throws InvalidObjectException {

        rating.verifyFields();

        ratingService.insertRatingToDb(rating);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<Rating> modifyRating(@RequestBody Rating rating){

        rating.verifyFields();

        ratingService.modifyRating(rating);
        return new ResponseEntity<Rating>(rating, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ratingId}")
    private ResponseEntity<SuccessObject> deleteRating(@PathVariable final Long ratingId) throws NoSuchObjectException {

        ratingService.deleteRating(ratingId);


        SuccessObject success = new SuccessObject(HttpStatus.OK,
                "User with id = " + ratingId + " successfully deleted");
        return new ResponseEntity<>(success, HttpStatus.OK);

    }
}
