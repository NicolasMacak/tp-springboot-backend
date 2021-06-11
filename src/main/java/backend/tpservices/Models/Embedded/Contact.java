package backend.tpservices.Models.Embedded;

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

    public Boolean isInvalid(){
        boolean  isValid = true;

        isValid &=  Pattern.matches(Constants.Regex.StringWithSpaces, firstName);
        isValid &= Pattern.matches(Constants.Regex.StringWithSpaces, lastName);
        isValid &= Pattern.matches(Constants.Regex.GlobalNumberFormat, phoneNumber);
        isValid &= Pattern.matches(Constants.Regex.Email, email);
        isValid &= Pattern.matches(Constants.Regex.Password, password);

        return !isValid;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
