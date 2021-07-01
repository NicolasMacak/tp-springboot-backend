package backend.tpservices.Modules.Review;

import backend.tpservices.Modules.Company.Company;
import backend.tpservices.Modules.Company.CompanyRepository;
import backend.tpservices.Modules.Courier.Courier;
import backend.tpservices.Modules.Courier.CourierRepository;
import backend.tpservices.Modules.Product.Product;
import backend.tpservices.Modules.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class ReviewService{
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CourierRepository courierRepository;

    @Transactional
    public boolean addCompanyReview(Long companyId, CompanyReview review) {
        Optional<Company> dbCompany = companyRepository.findById(companyId);
        boolean result = addReview(dbCompany.get(), review);
        companyRepository.save(dbCompany.get());
        return result;
    }

    @Transactional
    public boolean addProductReview(Long productId, ProductReview review) {
        Optional<Product> dbProduct = productRepository.findById(productId);
        boolean result = addReview(dbProduct.get(), review);
        productRepository.save(dbProduct.get());
        return result;
    }

    @Transactional
    public boolean addCourierReview(Long productId, CourierReview review) {
        Optional<Courier> dbCourier = courierRepository.findById(productId);
        boolean result = addReview(dbCourier.get(), review);
        courierRepository.save(dbCourier.get());
        return result;
    }

    private boolean addReview(Reviewable dbObject, Review review) {
        if(dbObject == null) return false;
        
        if (review.getDate() == null) review.setDate(new Date());

        reviewRepository.save(review);
        dbObject.addReview(review);
        return true;
    }
}
