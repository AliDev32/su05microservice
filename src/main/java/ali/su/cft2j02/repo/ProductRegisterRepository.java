package ali.su.cft2j02.repo;

import ali.su.cft2j02.entity.ProductRegister;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRegisterRepository extends CrudRepository<ProductRegister, Long> {
    List<ProductRegister> findProductRegistersByProductIdAndType(Long productId, String type);
}