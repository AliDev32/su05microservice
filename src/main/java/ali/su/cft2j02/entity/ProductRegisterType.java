package ali.su.cft2j02.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 167_Справочник типов регистров
 */
@Data
@Entity(name = "tpp_ref_product_register_type")
public class ProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prt_seq")
    @SequenceGenerator(name="prt_seq", sequenceName="table_id_seq", allocationSize=1)
    private Long internalId;

    private String value;

    private String registerTypeName;

    private String productClassCode;

    private String accountType;
}