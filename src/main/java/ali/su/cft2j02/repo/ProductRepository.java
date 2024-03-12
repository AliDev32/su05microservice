package ali.su.cft2j02.repo;

import ali.su.cft2j02.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findProductsByNumber(String number);
}
