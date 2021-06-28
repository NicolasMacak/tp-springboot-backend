package backend.tpservices.Modules.Product;

import Config.Constants.*;
import backend.tpservices.Modules.General.Exceptions.Declarations.InvalidFormatException;
import backend.tpservices.Modules.Review.ProductReview;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    ProductCategory category;
    ProductState state;
    String title;
    Double price;
    String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private List<ProductReview> reviewList = new ArrayList<>();

    public Product() {}

    public Product(ProductCategory category, ProductState state, String title, Double price, String description) {
        this.category = category;
        this.state = state;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public void update(Product product) {
        this.category = product.getCategory();
        this.state = product.getState();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    public void verifyFields(){ // socialny experiment. Vracia kde presne sa stala aka chyba
        String invalidFields = "";

        invalidFields += this.price > 0 ? "" : "Price: cant be < 0 ";

        if(!invalidFields.equals("")){
            throw new InvalidFormatException("Product id: "+this.id, invalidFields);
        }
    }

    public void addReview(ProductReview review) { this.reviewList.add(review);}

    public Long getId() { return id; }
    public ProductCategory getCategory() { return category; }
    public ProductState getState() { return state; }
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }

    public void setId(Long id) { this.id = id; }
    public void setCategory(ProductCategory category) { this.category = category; }
    public void setState(ProductState state) { this.state = state; }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
