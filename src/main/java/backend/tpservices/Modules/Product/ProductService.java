package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.Product.Product;
import backend.tpservices.Modules.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Optional<List<Product>> getAllProducts(){
        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add);
        return Optional.ofNullable(products.isEmpty()? null : products);
    }

    public void insertProductToDb(Product product){
        productRepository.save(product);
    }

    public Optional<Product> getProductById(Long id) { return productRepository.findById(id); }

    public void modifyProduct(Product product){
        Optional<Product> dbProduct = productRepository.findById(product.getId());
        if(dbProduct.isEmpty()) { return; }

        dbProduct.get().update(product);
        productRepository.save(dbProduct.get());
    }

    public void deleteProduct(Long id) throws NoSuchObjectException {
        Optional<Product> dbProduct = productRepository.findById(id);
        if(dbProduct.isEmpty()) {
            throw new NoSuchObjectException("Product id: "+id+" not found");
        }

        productRepository.delete(dbProduct.get());
    }
}
