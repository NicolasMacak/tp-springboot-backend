package backend.tpservices.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    enum State {
        inStorage,
        sold,
        ReadyToOrder,
        presale
    }

    enum Category {
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
    String title;
    Double price;
    String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    List<Rating> ratings;

    public Product(String title, Double price, String description, List<Rating> ratings) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.ratings = ratings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
