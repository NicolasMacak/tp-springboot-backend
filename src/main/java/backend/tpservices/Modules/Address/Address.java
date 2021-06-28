package backend.tpservices.Modules.Address;

import Config.Constants;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String streetName;
    private String streetNumber;
    private String city;
    private String postalCode;

    public Address() {}

    public Address(String streetName, String streetNumber, String city, String postalCode) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Boolean isValid(){
        return Pattern.matches(Constants.Regex.StringWithSpaces, this.streetName)
                && Pattern.matches(Constants.Regex.PostalCode, this.postalCode);
    }
    public Boolean isModifyValid(){
        return (this.streetName== null || Pattern.matches(Constants.Regex.StringWithSpaces, this.streetName))
                && (this.postalCode == null || Pattern.matches(Constants.Regex.PostalCode, this.postalCode));
    }

    public void update(Address address){
        this.streetName = address.getStreetName() != null ? address.getStreetName() : this.streetName ;
        this.streetNumber = address.getStreetNumber() != null ? address.getStreetNumber() : this.streetNumber;
        this.city = address.getCity() != null? address.getCity() : this.city;
        this.postalCode = address.getPostalCode() != null? address.getPostalCode() : this.postalCode;
    }

    //---------------------------------------------------

    public Long getId() {
        return id;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
