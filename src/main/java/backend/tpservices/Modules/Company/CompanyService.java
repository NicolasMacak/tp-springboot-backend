package backend.tpservices.Modules.Company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import backend.tpservices.Modules.Review.CompanyReview;
import backend.tpservices.Modules.Company.Company;
import backend.tpservices.Modules.Company.CompanyRepository;
import backend.tpservices.Modules.Review.CompanyReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyReviewRepository reviewRepository;

    public Optional<List<Company>> getAllCompanies(){
        List<Company> companyList = new ArrayList<>();
        companyRepository.findAll().forEach(companyList::add);
        return Optional.ofNullable(companyList.isEmpty()? null : companyList);
    }

    public Optional<Company> getCompanyById(Long id){
        return companyRepository.findById(id);
    }

    @Transactional
    public void insertCompanyToDb(Company student)
    {
    companyRepository.save(student);
    }

    @Transactional
    public boolean deleteCompany(Long id){
        Optional<Company> dbCompany = companyRepository.findById(id);
        if(dbCompany.isEmpty()) return false;

        companyRepository.delete(dbCompany.get());
        return true;
    }

    @Transactional
    public boolean modifyCompany(Company company){
        Optional<Company> dbCompany = companyRepository.findById(company.getId());

        if(dbCompany.isEmpty()) return false;

        dbCompany.get().update(company);
        companyRepository.save(dbCompany.get());
        return true;
    }

    @Transactional
    public boolean addCompanyReview(Long companyId, CompanyReview review) {
        Optional<Company> dbCompany = companyRepository.findById(companyId);

        if(dbCompany.isEmpty()) return false;

        if (review.getDate() == null) review.setDate(new Date());

        reviewRepository.save(review);
        dbCompany.get().addReview(review);
        companyRepository.save(dbCompany.get());

        return true;
    }

}  