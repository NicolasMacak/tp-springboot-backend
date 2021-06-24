package backend.tpservices.Services;

import backend.tpservices.Models.Rating;
import backend.tpservices.Repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Optional<List<Rating>> getAllRatings(){
        List<Rating> ratings = new ArrayList<>();

        ratingRepository.findAll().forEach(ratings::add);
        return Optional.ofNullable(ratings.isEmpty()? null : ratings);
    }

    public void insertRatingToDb(Rating rating){
        ratingRepository.save(rating);
    }

    public Optional<Rating> getRatingById(Long id) { return ratingRepository.findById(id); }

    public void modifyRating(Rating rating){
        Optional<Rating> dbRating = ratingRepository.findById(rating.getId());
        if(dbRating.isEmpty()) { return; }

        dbRating.get().update(rating);
        ratingRepository.save(dbRating.get());
    }

    public void deleteRating(Long id) throws NoSuchObjectException {
        Optional<Rating> dbRating = ratingRepository.findById(id);
        if(dbRating.isEmpty()) {
            throw new NoSuchObjectException("Rating id: "+id+" not found");
        }

        ratingRepository.delete(dbRating.get());
    }
}
