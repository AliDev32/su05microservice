package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Справочник Пула Счетов
 */
@Data
@Entity(name = "account_pool")
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accpool_seq")
    @SequenceGenerator(name = "accpool_seq", sequenceName = "table_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "mdm_code")
    private String mdmCode;

    @Column(name = "priority_code")
    private String priorityCode;

    @Column(name = "registry_type_code")
    private String registerTypeCode;
}