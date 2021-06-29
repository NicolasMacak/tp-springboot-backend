package backend.tpservices.Modules.Contact;

import Config.Constants;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;

    protected Contact() {}

    public Contact(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Boolean isValid() {
        return Pattern.matches(Constants.Regex.StringWithSpaces, firstName) &&
               Pattern.matches(Constants.Regex.StringWithSpaces, lastName) &&
               Pattern.matches(Constants.Regex.GlobalNumberFormat, phoneNumber) &&
               Pattern.matches(Constants.Regex.Email, email) &&
               Pattern.matches(Constants.Regex.Password, password);
    }

    public Boolean isModifyValid() {
        return (this.firstName== null || Pattern.matches(Constants.Regex.StringWithSpaces, this.firstName)) &&
               (this.lastName == null || Pattern.matches(Constants.Regex.StringWithSpaces, this.lastName)) &&
               (this.phoneNumber == null || Pattern.matches(Constants.Regex.GlobalNumberFormat, this.phoneNumber)) &&
               (this.email == null || Pattern.matches(Constants.Regex.Email, this.email)) &&
               (this.password == null || Pattern.matches(Constants.Regex.Password, this.password));
    }

    public void update(Contact contact){
        this.firstName = contact.getFirstName() == null? this.firstName : contact.getFirstName();
        this.lastName = contact.getLastName() == null? this.lastName : contact.getLastName();
        this.phoneNumber = contact.getPhoneNumber() == null? this.phoneNumber : contact.getPhoneNumber();
        this.email = contact.getEmail() == null? this.email : contact.getEmail();
        this.password = contact.getPassword() == null? this.password : contact.getPassword();
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
