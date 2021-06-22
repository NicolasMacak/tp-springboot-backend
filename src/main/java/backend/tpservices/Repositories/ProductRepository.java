package backend.tpservices.Repositories;

import backend.tpservices.Models.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
