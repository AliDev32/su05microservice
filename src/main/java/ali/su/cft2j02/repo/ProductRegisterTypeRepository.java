package ali.su.cft2j02.repo;


import ali.su.cft2j02.entity.ProductRegisterType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRegisterTypeRepository extends CrudRepository<ProductRegisterType, Long> {
    List<ProductRegisterType> findProductRegisterTypesByProductClassCode(String productClassCode);
    List<ProductRegisterType> findProductRegisterTypesByProductClassCodeAndAccountType(String productClassCode, String accountType);
}