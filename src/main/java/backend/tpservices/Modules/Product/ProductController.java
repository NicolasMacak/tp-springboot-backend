package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.General.ResponseObjects.ResponseObject;
import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import backend.tpservices.Modules.Review.ProductReview;
import backend.tpservices.Modules.Review.Review;
import backend.tpservices.Modules.Review.ReviewService;
import backend.tpservices.Modules.Order.Order;
import backend.tpservices.TpServicesApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ReviewService reviewService;

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

    @PutMapping(value = "/{productId}")
    private ResponseEntity<Product> modifyProduct(@PathVariable final Long productId,
                                                  @RequestBody Product product){

        product.verifyFields();

        productService.modifyProduct(productId, product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{productId}")
    private ResponseEntity<SuccessObject> deleteProduct(@PathVariable final Long productId) throws NoSuchObjectException {

           productService.deleteProduct(productId);


       SuccessObject success = new SuccessObject(HttpStatus.OK,
               "User with id = " + productId + " successfully deleted");
       return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PutMapping(value = "/multiedit")
    private ResponseEntity<ResponseObject> multipleProductEdit(@RequestBody List<Product> products) throws NoSuchObjectException {
        Map<Long, Product> productMap = Product.listToMap(products);

        List<Product> updatedProducts = productService.editProducts(productMap);

        SuccessObject success = new SuccessObject(updatedProducts,
                products.size() == updatedProducts.size() ? "" : products.size() - updatedProducts.size()+" items not updated. Missing id or not found in database");

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @GetMapping("/{productId}/review")
    private List<Review> getReviewsById(@PathVariable("productId") Long productId) throws NoSuchObjectException {
        Optional<Product> optProduct = productService.getProductById(productId);
        if (optProduct.isPresent()) {

            List<Review> reviews = optProduct.get().getReviewList();
            if(reviews.isEmpty()) throw new NoResultException();

            return reviews;
        }
        throw new NoSuchObjectException("Product with productId = " + productId + " not found");
    }

    @PostMapping("/{productId}/review")
    private ResponseEntity<SuccessObject> addReview(@PathVariable("productId") Long productId,
                                                    @RequestBody ProductReview review) throws InvalidObjectException, NoSuchObjectException {

        if (!review.isValid()) throw new InvalidObjectException("Invalid review fields");

        if (reviewService.addProductReview(productId, review)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "Review successfully added");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Product with productId = " + productId + " not found");
    }
}
