package ali.su.cft2j02.repo;

import ali.su.cft2j02.entity.AccountPool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPoolRepository extends CrudRepository<AccountPool, Long> {
    AccountPool findAccountPoolByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegisterTypeCode(
            String branchCode, String currencyCode, String mdmCode, String priorityCode, String registerTypeCode
                                                                                                      );
}