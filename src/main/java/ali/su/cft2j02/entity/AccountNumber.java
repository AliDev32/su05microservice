package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Справочник номеров Счетов
 */
@Data
@Entity(name = "account")
public class AccountNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accnum_seq")
    @SequenceGenerator(name = "accnum_seq", sequenceName = "table_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "account_pool_id")
    private Long accountPoolId;

    @Column(name = "account_number")
    private String accountNumber;
}