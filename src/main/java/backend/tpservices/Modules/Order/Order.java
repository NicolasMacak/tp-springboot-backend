package backend.tpservices.Modules.Order;

import backend.tpservices.Modules.Client.Client;
import backend.tpservices.Modules.General.Exceptions.Declarations.InvalidFormatException;
import backend.tpservices.Modules.General.Interfaces.Entity;
import backend.tpservices.Modules.Product.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "Orders")
public class Order implements Entity<Order> {

    public Order() {}

    public Order(Client client, PaymentType paymentType, List<Product> products) {
        this.client = client;
        this.paymentType = paymentType;
        this.products = products;
    }

    public enum PaymentType{
        online,
        cash
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private PaymentType paymentType;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Product> products;

    private double totalPrice = 0;

    public void update(Order order){
        this.paymentType = order.getPaymentType();
        updateProducts(order.getProducts());
    }

    /*
    totok bude trošku väčšia zábavička. Chcem tu pracovať iba s existujúcimi produktami. Nechcem persistovať nové.
    Takže budem musieť vytiahnuť z db produkty a priradiť ich. Nemôžem sem vkladať nové objekty.

    Solution ktoré ma napadá je z listu vytiahnuť idčka produktov, vytiahnuť podľa nich patričné produkty z DB
    a tieto JPA objetky priradiť do zoznamu. To by malo fixnúť problém.
     */

    public void updateProducts(List<Product> newProducts){ // Nejde to. Ani pridávať, ani replacnúť. Vyzerá to tak že updatovať listy v JPA je zábavička
        this.products.addAll(newProducts);
//        this.products = new ArrayList<>(newProducts);
    }


    public void verifyFields(){
        String invalidFields = "";

        invalidFields += this.totalPrice > 0 ? "" : "Price: cant be < 0 ";
        invalidFields += this.products.isEmpty() ? "Missing products" : "";

        if(!invalidFields.equals("")){
            throw new InvalidFormatException("Order id: "+this.id, invalidFields);
        }
    }

    public Long getId() { return id; }
    public Client getClient() { return client; }
    public PaymentType getPaymentType() { return paymentType; }
    public List<Product> getProducts() { return products; }
    public double getTotalPrice() { return totalPrice; }

    public void setId(Long id) { this.id = id; }
    public void setClient(Client client) { this.client = client; }
    public void setPaymentType(PaymentType paymentType) { this.paymentType = paymentType; }
    public void setProducts(List<Product> products) { this.products = products; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
}
