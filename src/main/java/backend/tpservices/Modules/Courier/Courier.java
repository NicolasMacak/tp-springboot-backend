package backend.tpservices.Modules.Courier;

import backend.tpservices.Modules.Contact.Contact;
import backend.tpservices.Modules.Order.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Contact contact;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "courier_id")
    private List<Order> orderList = new ArrayList<>();

    public Courier() {}
    public Courier(Contact contact) { this.contact = contact;}

    public Long getId() { return id; }
    public Contact getContact() { return contact; }
    public List<Order> getOrders() { return orderList; }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    public void setId(Long id) { this.id = id; }
    public void setOrders(List<Order> orderList) { this.orderList = orderList; }

    public void update(Courier courier){
        this.contact.update(courier.getContact());
    }

    @Override
    public String toString() { return "Courier{" + "id=" + id + ", contact=" + contact + '}'; }
}
