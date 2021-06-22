package backend.tpservices.Controllers;

import java.io.InvalidObjectException;
import java.rmi.NoSuchObjectException;
import java.util.List;

import backend.tpservices.Models.UserTypes.Company;
import backend.tpservices.Services.CompanyService;
import backend.tpservices.Models.General.SuccessObject;
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

    @PostMapping()
    private ResponseEntity<SuccessObject> addCompany(@RequestBody Company company) throws InvalidObjectException {

        if(!company.getAddress().isValid()) throw new InvalidObjectException("Invalid company address fields");
        if(!company.isValid()) throw new InvalidObjectException("Invalid company info fields");

        companyService.insertCompanyToDb(company);
        SuccessObject success = new SuccessObject(HttpStatus.OK,"Company " + company.getId() + " successfully added");

        return new ResponseEntity<>(success, HttpStatus.OK);
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
