package ali.su.cft2j02.mapping;

import ali.su.cft2j02.prodregister.messages.ProductRegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductRegRequestMap {
    private ProductRegisterRequest productRegRequest;
    private Long accountId;
}