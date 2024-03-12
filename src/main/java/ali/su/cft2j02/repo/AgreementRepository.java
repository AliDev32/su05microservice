package ali.su.cft2j02.repo;


import ali.su.cft2j02.entity.Agreement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends CrudRepository<Agreement, Long> {
    List<Agreement> findAgreementsByNumber(String number);
}
