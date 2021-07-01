package backend.tpservices.Modules.Review;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class CourierReview extends Review {

    public CourierReview() {super();}
    public CourierReview(String reviewer, Date date, String title, String text, Integer rating) {
        super(reviewer, date, title, text, rating);
    }

}
