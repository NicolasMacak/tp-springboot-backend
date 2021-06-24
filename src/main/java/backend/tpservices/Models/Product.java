package backend.tpservices.Models;

import Config.Constants;
import backend.tpservices.Models.Exceptions.InvalidFormatException;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "Products")
public class Product {

   public enum State {
        inStorage,
        sold,
        ReadyToOrder,
        presale
    }

   public enum Category {
        electronics,
        whiteGoods,
        houseAndGarden,
        breeding,
        babyGoods,
        booksMoviesGames,
        groceries,
        cosmeticsAndHealth,
        clothes,
        sport,
        furniture
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    Category category;
    State state;
    String title;
    Double price;
    String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRODUCT_ID")
    List<Rating> ratings;

    public Product() {}

    public Product(Category category, State state, String title, Double price, String description, List<Rating> ratings) {
        this.category = category;
        this.state = state;
        this.title = title;
        this.price = price;
        this.description = description;
        this.ratings = ratings;
    }

    public void update(Product product) {
        this.category = product.getCategory();
        this.state = product.getState();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        //this.ratings = product.getRatings();
    }

    public void verifyFields(){ // socialny experiment. Vracia kde presne sa stala aka chyba
        String invalidFields = "";

        invalidFields += this.price > 0 ? "" : "Price: cant be < 0 ";

        if(!invalidFields.equals("")){
            throw new InvalidFormatException("Product id: "+this.id, invalidFields);
        }

        for(Rating rating: this.ratings){
            rating.verifyFields();
        }
    }

    public Long getId() { return id; }
    public Category getCategory() { return category; }
    public State getState() { return state; }
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public List<Rating> getRatings() {
        return ratings;
    }

    public void setId(Long id) { this.id = id; }
    public void setCategory(Category category) { this.category = category; }
    public void setState(State state) { this.state = state; }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
