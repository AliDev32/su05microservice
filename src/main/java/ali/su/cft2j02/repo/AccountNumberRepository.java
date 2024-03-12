package ali.su.cft2j02.repo;

import ali.su.cft2j02.entity.AccountNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountNumberRepository extends CrudRepository<AccountNumber, Long> {
    List<AccountNumber> findAccountNumbersByAccountPoolIdOrderById(Long accountPoolId);
}