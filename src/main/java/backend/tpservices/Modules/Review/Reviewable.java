package backend.tpservices.Modules.Review;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reviewable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reviewable_id")
    List<Review> reviewList = new ArrayList<>();

    public Long getId() { return id; }
    public List<Review> getReviewList() { return this.reviewList;}
    public void addReview(Review review) {this.reviewList.add(review);}
}
