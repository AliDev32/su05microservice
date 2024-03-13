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

    private Long accountPoolId;

    private String accountNumber;
}