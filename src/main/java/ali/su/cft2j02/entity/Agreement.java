package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Доп. соглашение
 */
@Data
@Entity(name = "agreement")
@NoArgsConstructor
public class Agreement {
    /**
     * ID доп соглашения
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agreem_seq")
    @SequenceGenerator(name = "agreem_seq", sequenceName = "table_id_seq", allocationSize = 1)
    private Long id;

    /**
     * ID продукта
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * Номер ДС
     */
    private String number;
}