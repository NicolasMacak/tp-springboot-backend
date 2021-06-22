package backend.tpservices.Models;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String description;
    int starCount;

    public Rating(String description, int starCount) {
        this.description = description;
        this.starCount = starCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }
}
