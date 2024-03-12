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
    @Column(name = "internal_id")
    private Long internalId;

    @Column(name = "value_code")
    private String value;

    @Column(name = "gbl_code")
    private String gblCode;

    @Column(name = "gbl_name")
    private String gblName;

    @Column(name = "product_row_code")
    private String productRowCode;

    @Column(name = "product_row_name")
    private String productRowName;

    @Column(name = "subclass_code")
    private String subclassCode;

    @Column(name = "subclass_name")
    private String subclassName;
}