package ali.su.cft2j02.repo;

import ali.su.cft2j02.entity.ProductClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductClassRepository extends CrudRepository<ProductClass, Long> {
    ProductClass findProductClassByValue(String value);
    ProductClass findProductClassByInternalId(Long internalId);
}
