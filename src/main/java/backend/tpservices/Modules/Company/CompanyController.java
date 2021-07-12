package backend.tpservices.Modules.Company;

import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

import backend.tpservices.Modules.General.ResponseObjects.PaginatedObject;
import backend.tpservices.Modules.Product.Product;
import backend.tpservices.Modules.Product.ProductService;
import backend.tpservices.Modules.Review.CompanyReview;
import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import backend.tpservices.Modules.Review.Review;
import backend.tpservices.Modules.Review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductService productService;

    @GetMapping()
    private ResponseEntity<PaginatedObject> getAllCompanies(@RequestParam(defaultValue = "0") Integer pageNo,
                                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                                            @RequestParam (value = "sortBy", defaultValue = "id") String sortBy,
                                                            @RequestParam (value = "order", defaultValue = "asc") String order) {

        Sort.Direction direction = order.equals("asc") ?  Sort.Direction.ASC : Sort.Direction.DESC;

        Page<Company> companiesPage = companyService.getAllCompanies(pageNo, pageSize, sortBy, direction)
                                                    .orElseThrow(NoResultException::new);

        PaginatedObject paginatedCompanyList =  new PaginatedObject(companiesPage.getContent(),
                                                                    companiesPage.getTotalElements(),
                                                                    companiesPage.getTotalPages(),
                                                                    companiesPage.getNumber());

        return new ResponseEntity<>(paginatedCompanyList, HttpStatus.OK);

    }

    @GetMapping("/{companyId}")
    private ResponseEntity<Company> getCompanyById(@PathVariable("companyId") Long companyId) throws NoSuchObjectException {
        Company company = companyService
                .getCompanyById(companyId)
                .orElseThrow(() -> new NoSuchObjectException("Company with companyId = " + companyId + " not found"));

        return new ResponseEntity<>(company,HttpStatus.OK);
    }

    @GetMapping("/{companyId}/review")
    private ResponseEntity<List<Review>> getReviewsById(@PathVariable("companyId") Long companyId) throws NoSuchObjectException {
        Optional<Company> optCompany = companyService.getCompanyById(companyId);
        if (optCompany.isPresent()) {
            List<Review> reviews = optCompany.get().getReviewList();
            if(reviews.isEmpty()) throw new NoResultException();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Company with companyId = " + companyId + " not found");
    }

    @GetMapping("/{companyId}/product")
    private ResponseEntity<List<Product>> getProductsById(@PathVariable("companyId") Long companyId) throws NoSuchObjectException {
        Optional<Company> optCompany = companyService.getCompanyById(companyId);
        if (optCompany.isPresent()) {
            List<Product> products = optCompany.get().getProductList();
            if(products.isEmpty()) throw new NoResultException();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Company with companyId = " + companyId + " not found");
    }

    @PostMapping()
    private ResponseEntity<SuccessObject> addCompany(@RequestBody Company company) throws InvalidObjectException {

        if(!company.getAddress().isValid()) throw new InvalidObjectException("Invalid company address fields");
        if(!company.isValid()) throw new InvalidObjectException("Invalid company info fields");

        companyService.insertCompanyToDb(company);
        SuccessObject success = new SuccessObject(HttpStatus.OK,"Company " + company.getId() + " successfully added");

        return new ResponseEntity<>(success, HttpStatus.OK);
    }

    @PostMapping("/{companyId}/review")
    private ResponseEntity<SuccessObject> addReview(@PathVariable("companyId") Long companyId,
                                                    @RequestBody CompanyReview review) throws InvalidObjectException, NoSuchObjectException {

        if(!review.isValid()) throw new InvalidObjectException("Invalid review fields");

        if (reviewService.addCompanyReview(companyId,review)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                                              "Review successfully added");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Company with companyId = "+ companyId +" not found");
    }

    @PostMapping("/{companyId}/product")
    private ResponseEntity<SuccessObject> addProduct(@PathVariable("companyId") Long companyId,
                                                    @RequestBody Product product) throws NoSuchObjectException {

        product.verifyFields();
//        if(!product.isValid()) throw new InvalidObjectException("Invalid product fields");

        if (productService.addCompanyProduct(companyId,product)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,"Product successfully added");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Company with companyId = "+ companyId +" not found");
    }

    @DeleteMapping("/{companyId}")
    private ResponseEntity<SuccessObject> deleteCompany(@PathVariable("companyId") Long companyId) throws NoSuchObjectException {
        if(companyService.deleteCompany(companyId)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "Company with companyId = " + companyId + " successfully deleted");
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        throw new NoSuchObjectException("Company with companyId = "+ companyId +" not found");
    }

    @PutMapping(value = "/{companyId}")
    public ResponseEntity<SuccessObject> modifyCompany(@PathVariable("companyId") Long companyId,
                                                       @RequestBody Company company) throws InvalidObjectException, NoSuchObjectException {

        if(!company.getAddress().isModifyValid()) throw new InvalidObjectException("Invalid company address fields");
        if(!company.isModifyValid()) throw new InvalidObjectException("Invalid company info fields");

        if (companyService.modifyCompany(companyId, company)){
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "Company with id = " + companyId + " successfully modified");
            return new ResponseEntity<>(success, HttpStatus.OK);
            // TODO changed fields?
        }

        throw new NoSuchObjectException("Company with id = "+ companyId +" not found");

    }
}
