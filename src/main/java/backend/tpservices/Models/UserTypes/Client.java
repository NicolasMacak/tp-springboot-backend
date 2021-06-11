package backend.tpservices.Models.UserTypes;

import backend.tpservices.Models.Embedded.Contact;

import javax.persistence.*;
import java.beans.Customizer;

@Entity
@Table(name = "Clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Contact contact;

    public Client() {}
/*
    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }*/
/*
    @Override
    public String toString() {
        return String.format(
                "Client[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
*/
    public Long getId() { return id; }
    public Contact getContact() { return contact; }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
