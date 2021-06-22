package backend.tpservices.Services;

import backend.tpservices.Models.Product;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<List<Product>> getAllProducts(){
        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add); // dlhsi zapis je .foreach(client -> clients.add(client)) (brave new world)
        return Optional.ofNullable(products.isEmpty()? null : products);
    }

    public void insertProductToDb(Product product){
        productRepository.save(product);
    }
}
