package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ЭП. Экземпляр Продукта
 */
@Data
@Entity(name = "tpp_product")
@NoArgsConstructor
public class Product {
    /**
     * ID экземпляра продукта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name="product_seq", sequenceName="table_id_seq", allocationSize=1)
    private Long id;

    /**
     * Код продукта в каталоге продуктов
     */
    private Long productCodeId;

    /**
     * ID Клиента
     */
    private Long clientId;

    /**
     * Тип Экземпляра Продукта
     */
    private String type;

    /**
     * Номер договора
     */
    private String number;

    /**
     * Приоритет
     */
    private Long priority;

    private Date dateOfConclusion;

    private Date startDateTime;

    private Date endDateTime;

    private Long days;

    /**
     * Штрафная процентная ставка
     */
    private Double penaltyRate;

    private Double nso;

    private Double thresholdAmount;

    private String requisiteType;

    private String interestRateType;

    private Double taxRate;

    @Column(name = "reasone_close")
    private String reasonClose;

    private String state;
}