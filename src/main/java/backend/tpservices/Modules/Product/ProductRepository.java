package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.Product.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
