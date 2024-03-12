package ali.su.cft2j02.mapping;

import ali.su.cft2j02.entity.ProductClass;
import ali.su.cft2j02.product.messages.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductRequestMap {
    private ProductRequest productRequest;
    private ProductClass productClass;
}