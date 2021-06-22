package backend.tpservices.Repositories;


import backend.tpservices.Models.Embedded.Reviews.CompanyReview;
import org.springframework.data.repository.CrudRepository;

public interface CompanyReviewRepository extends CrudRepository<CompanyReview, Long> {}