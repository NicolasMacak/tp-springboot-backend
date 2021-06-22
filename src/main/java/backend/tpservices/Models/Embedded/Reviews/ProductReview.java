package backend.tpservices.Models.Embedded.Reviews;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ProductReview extends Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Product product;

    public ProductReview() {super();}

    public ProductReview(String reviewer, Date date, String title, String text, Integer rating) {
        super(reviewer, date, title, text, rating);
    }

    // --------------------------------

//    public Product getProduct() {
//        return product;
//    }
//    public void setProduct(Product product) {
//        this.product = product;
//    }
    public Long getId() {
        return id;
    }

}
