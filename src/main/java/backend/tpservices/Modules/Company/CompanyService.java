package backend.tpservices.Modules.Company;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service  
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public  Optional<Page<Company>> getAllCompanies(Integer pageNo, Integer pageSize,
                                                    String sortBy, Sort.Direction direction) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));

        Page<Company> pagedResult = companyRepository.findAll(paging);

        return pagedResult.hasContent() ? Optional.of(pagedResult) : Optional.empty();
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
    public boolean modifyCompany(Long id, Company company){
        Optional<Company> dbCompany = companyRepository.findById(id);

        if(dbCompany.isEmpty()) return false;

        dbCompany.get().update(company);
        companyRepository.save(dbCompany.get());
        return true;
    }

}  