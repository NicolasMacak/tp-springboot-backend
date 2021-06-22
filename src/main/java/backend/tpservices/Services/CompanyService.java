package backend.tpservices.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import backend.tpservices.Models.UserTypes.Company;
import backend.tpservices.Repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

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

    public boolean deleteCompany(Long id){
        Optional<Company> dbCompany = companyRepository.findById(id);
        if(dbCompany.isEmpty()) return false;

        companyRepository.delete(dbCompany.get());
        return true;
}

    public boolean modifyCompany(Company company){
        Optional<Company> dbCompany = companyRepository.findById(company.getId());

        if(dbCompany.isEmpty()) return false;

        dbCompany.get().update(company);



        companyRepository.save(dbCompany.get());

        return true;
    }

}  