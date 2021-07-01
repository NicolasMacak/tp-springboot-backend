package backend.tpservices.Modules.Courier;

import backend.tpservices.Modules.Contact.Contact;
import backend.tpservices.Modules.Review.Reviewable;
import javax.persistence.*;

@Entity
public class Courier extends Reviewable {

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Contact contact;

//    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "courier_id")
//    private List<Order> orderList = new ArrayList<>();

    public Courier() {}
    public Courier(Contact contact) { this.contact = contact;}


    public Contact getContact() { return contact; }
    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public void update(Courier courier){
        this.contact.update(courier.getContact());
    }

    @Override
    public String toString() { return "Courier{" + ", contact=" + contact + '}'; }


}
