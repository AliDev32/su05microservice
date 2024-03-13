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

    private String branchCode;

    private String currencyCode;

    private String mdmCode;

    private String priorityCode;

    @Column(name = "registry_type_code")
    private String registerTypeCode;
}