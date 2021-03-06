package backend.tpservices.Modules.Product;

import Config.Constants.*;
import backend.tpservices.Modules.General.Exceptions.Declarations.InvalidFormatException;
import backend.tpservices.Modules.Review.Reviewable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Products")
public class Product extends Reviewable {

    ProductCategory category;
    ProductState state;
    String title;
    Double price;
    String description;

    public Product() {}

    public Product(ProductCategory category, ProductState state, String title, Double price, String description) {
        this.category = category;
        this.state = state;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public void update(Product product) {
        this.category = product.getCategory();
        this.state = product.getState();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

    public static Map<Long, Product> listToMap(List<Product> products){
        Map<Long, Product> productsMap = new HashMap<>();

        for(Product product: products){
            if(product.getId() == null){
                continue;
            }

            Long productId = product.getId();
            productsMap.put(productId, product);
        }

        return productsMap;
    }

    public void verifyFields(){ // socialny experiment. Vracia kde presne sa stala aka chyba
        String invalidFields = "";

        invalidFields += this.price > 0 ? "" : "Price: cant be < 0 ";

        if(!invalidFields.equals("")){
            throw new InvalidFormatException("Product id: "+this.id, invalidFields);
        }
    }

    public ProductCategory getCategory() { return category; }
    public ProductState getState() { return state; }
    public String getTitle() {
        return title;
    }
    public Double getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }

    public void setCategory(ProductCategory category) { this.category = category; }
    public void setState(ProductState state) { this.state = state; }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
