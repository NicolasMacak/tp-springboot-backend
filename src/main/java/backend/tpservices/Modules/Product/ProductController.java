package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    private ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts().orElseThrow(NoResultException::new);

        return products.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
                new ResponseEntity<>(products, HttpStatus.OK);

    }

    @GetMapping(value = "/{productId}")
    private ResponseEntity<Product> getProductById(@PathVariable final Long productId) throws NoSuchObjectException {
        Product product = productService
                .getProductById(productId)
                .orElseThrow(() -> new NoSuchObjectException("Product with id = "+productId+" not found"));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Product> addProduct(@RequestBody Product product) throws InvalidObjectException {

        product.verifyFields();

        productService.insertProductToDb(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<Product> modifyProduct(@RequestBody Product product){

        product.verifyFields();

        productService.modifyProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    private ResponseEntity<SuccessObject> deleteProduct(@PathVariable final Long productId) throws NoSuchObjectException {

           productService.deleteProduct(productId);


       SuccessObject success = new SuccessObject(HttpStatus.OK,
               "User with id = " + productId + " successfully deleted");
       return new ResponseEntity<>(success, HttpStatus.OK);
    }
}
