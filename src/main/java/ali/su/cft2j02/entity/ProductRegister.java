package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ПР. Продуктовый регистр
 */
@Data
@Entity(name = "tpp_product_register")
@NoArgsConstructor
public class ProductRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reg_seq")
    @SequenceGenerator(name="reg_seq", sequenceName="table_id_seq", allocationSize=1)
    private Long id;

    private Long productId;

    private String type;

    @Column(name = "account")
    private Long accountId;

    private String currencyCode;

    private String state;

    private String accountNumber;
}