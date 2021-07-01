package backend.tpservices.Modules.Order;

import backend.tpservices.Modules.Client.Client;
import backend.tpservices.Modules.General.Exceptions.Declarations.InvalidFormatException;
import backend.tpservices.Modules.General.Interfaces.EntityInterface;
import backend.tpservices.Modules.Product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order implements EntityInterface<Order> {

    public Order() {}

    public Order(Client client, PaymentType paymentType, List<Product> products) {
        this.client = client;
        this.paymentType = paymentType;
        this.products = products;

        updateProducts(products);
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

    @OneToMany
    private List<Product> products;

    private double totalPrice = 0;

    public void update(Order order){
        this.paymentType = order.getPaymentType();
        updateProducts(order.getProducts());
    }

    public void updateProducts(List<Product> newProducts){
        this.products.clear();
        this.products.addAll(newProducts);
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
