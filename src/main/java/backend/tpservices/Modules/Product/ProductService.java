package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.Product.Product;
import backend.tpservices.Modules.Product.ProductRepository;
import backend.tpservices.TpServicesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(TpServicesApplication.class);

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

    public void modifyProduct(Long id, Product product){
        Optional<Product> dbProduct = productRepository.findById(id);
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

    public List<Product> editProducts(Map<Long, Product> products){
        List<Product> dbProducts = productRepository.findProductsByIdList(products.keySet());

        for(Product dbProduct: dbProducts){
            dbProduct.update(products.get(dbProduct.getId()));
        }

        return  (List<Product>) productRepository.saveAll(dbProducts);
    }
}
