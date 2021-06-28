package backend.tpservices.Modules.Company;

import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

import backend.tpservices.Modules.Review.CompanyReview;
import backend.tpservices.Modules.General.ResponseObjects.SuccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping()
    private List<Company> getAllCompanies() {
        return companyService.getAllCompanies().orElseThrow(NoResultException::new);
    }

    @GetMapping("/{companyId}")
    private Company getCompanyById(@PathVariable("companyId") Long companyId) throws NoSuchObjectException {

        return companyService.getCompanyById(companyId)
                             .orElseThrow(() -> new NoSuchObjectException("Company with companyId = " +
                                                                          companyId + " not found"));
    }

    @GetMapping("/{companyId}/review")
    private List<CompanyReview> getReviewsById(@PathVariable("companyId") Long companyId) throws NoSuchObjectException {
        Optional<Company> optCompany = companyService.getCompanyById(companyId);
        if (optCompany.isPresent()) {

            List<CompanyReview> reviews = optCompany.get().getReviewList();
            if(reviews.isEmpty()) throw new NoResultException();

            return reviews;
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

        if (companyService.addCompanyReview(companyId,review)) {
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                                              "Review successfully added");
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

    @PutMapping()
    public ResponseEntity<SuccessObject> modifyCompany(@RequestBody Company company) throws InvalidObjectException, NoSuchObjectException {

        if(!company.getAddress().isModifyValid()) throw new InvalidObjectException("Invalid company address fields");
        if(!company.isModifyValid()) throw new InvalidObjectException("Invalid company info fields");

        if (companyService.modifyCompany(company)){
            SuccessObject success = new SuccessObject(HttpStatus.OK,
                    "Company with id = " + company.getId() + " successfully modified");
            return new ResponseEntity<>(success, HttpStatus.OK);
            // TODO changed fields?
        }

        throw new NoSuchObjectException("Company with id = "+ company.getId() +" not found");

    }
}
