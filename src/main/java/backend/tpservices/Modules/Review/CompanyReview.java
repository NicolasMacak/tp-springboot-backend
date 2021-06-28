package backend.tpservices.Modules.Review;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class CompanyReview extends Review {

    public CompanyReview() {super();}
    public CompanyReview(String reviewer, Date date, String title, String text, Integer rating) {
        super(reviewer, date, title, text, rating);
    }

}
