package backend.tpservices.Modules.Product;

import backend.tpservices.Modules.Product.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> findProductsByIdList(@Param("ids") Collection<Long> ids);
}
