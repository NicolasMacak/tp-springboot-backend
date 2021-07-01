package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.Company.Company;
import backend.tpservices.Modules.Company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CompanyRepository companyRepository;

    public Optional<List<Product>> getAllProducts(){
        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(products::add);
        return Optional.ofNullable(products.isEmpty()? null : products);
    }

    public void insertProductToDb(Product product){
        productRepository.save(product);
    }

    @Transactional
    public boolean addCompanyProduct(Long companyId, Product product) {
        Optional<Company> dbCompany = companyRepository.findById(companyId);
        if(dbCompany.isEmpty()) return false;

        insertProductToDb(product);
        dbCompany.get().addProduct(product);
        companyRepository.save(dbCompany.get());
        return true;
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
}
