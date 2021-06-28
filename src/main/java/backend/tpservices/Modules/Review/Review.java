package backend.tpservices.Modules.Review;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String reviewer;
    @Temporal(TemporalType.DATE)
    protected Date date;
    protected String title;
    protected String text;
    protected Integer rating;

    public Review() {}

    public Review(String reviewer, Date date, String title, String text, Integer rating) {
        this.reviewer = reviewer;
        this.date = date;
        this.title = title;
        this.text = text;
        this.rating = rating;
    }

    public boolean isValid() {
        try {
            return this.reviewer.length() > 0 && this.title.length() > 0 && this.text.length() > 0 &&
                   this.rating >= 0 && this.rating <= 5;
        }
        catch(Exception e) {return false;}
    }

    //-------------------------------------------------

    public String getReviewer() {
        return reviewer;
    }
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewer='" + reviewer + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                '}';
    }
}
