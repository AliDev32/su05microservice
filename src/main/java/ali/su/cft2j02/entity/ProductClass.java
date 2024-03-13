package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 172_Справочник Класс продукта Банка (Каталог Продуктов)
 */
@Data
@Entity(name = "tpp_ref_product_class")
@NoArgsConstructor
public class ProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pc_seq")
    @SequenceGenerator(name="pc_seq", sequenceName="table_id_seq", allocationSize=1)
    private Long internalId;

    private String value;

    private String gbiCode;

    private String gbiName;

    private String productRowCode;

    private String productRowName;

    private String subclassCode;

    private String subclassName;
}