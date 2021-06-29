package backend.tpservices.Modules.Courier;

import backend.tpservices.Modules.Contact.Contact;
import javax.persistence.*;


@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Contact contact;

//    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "courier_id")
//    private List<Order> orderList = new ArrayList<>();

    public Courier() {}
    public Courier(Contact contact) { this.contact = contact;}

    public Long getId() { return id; }
    public Contact getContact() { return contact; }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void update(Courier courier){
        this.contact.update(courier.getContact());
    }

    @Override
    public String toString() { return "Courier{" + "id=" + id + ", contact=" + contact + '}'; }
}
