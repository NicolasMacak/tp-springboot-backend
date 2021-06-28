package backend.tpservices.Modules.Review;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ProductReview extends Review {

    public ProductReview() {super();}
    public ProductReview(String reviewer, Date date, String title, String text, Integer rating) {
        super(reviewer, date, title, text, rating);
    }

}
