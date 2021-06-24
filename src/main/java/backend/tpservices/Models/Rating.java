package backend.tpservices.Models;

import backend.tpservices.Models.Exceptions.InvalidFormatException;

import javax.persistence.*;

@Entity
@Table(name = "Ratings")
public class Rating {

    public Rating() {}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int starCount;

    public Rating(String description, int starCount) {
        this.description = description;
        this.starCount = starCount;
    }

    public void update(Rating rating){
        this.description = rating.getDescription();
        this.starCount = rating.getStarCount();
    }


    public void verifyFields(){
        if(this.starCount < 1 || this.starCount > 5){
            throw  new InvalidFormatException("Rating id: "+this.id, "Star count: has to be <=6 and >=0");
        }
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public int getStarCount() { return starCount; }

    public void setId(Long id) { this.id = id; }
    public void setDescription(String description) { this.description = description; }
    public void setStarCount(int starCount) { this.starCount = starCount; }
}
