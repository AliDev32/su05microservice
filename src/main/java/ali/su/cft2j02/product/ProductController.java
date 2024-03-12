package ali.su.cft2j02.product;

import ali.su.cft2j02.agreement.AgreementService;
import ali.su.cft2j02.product.messages.ProductRequest;
import ali.su.cft2j02.product.messages.ProductResponse;
import ali.su.cft2j02.utils.ResponseMaker;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Setter
@RequestMapping(path = "${rest.product-endpoint}", produces = "application/json")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AgreementService agreementService;

    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<ProductResponse> postRequest(@RequestBody ProductRequest request) {
        log.info("get post request - {}", request);
        try {
            // Проверка Request
            ProductResponse response = productService.validateRequest(request);
            if (response != null) {
                return getResponseEntity(response);
            }

            // Создание нового экземпляра продукта
            if (request.getInstanceId() == null) {
                return getResponseEntity(productService.createProduct(request));
            }

            // Создание нового доп соглашения к существующему договору
            return getResponseEntity(agreementService.createAgreement(request));
        }
        catch (Exception e) {
            log.info(e.toString());
            return getResponseEntity(ResponseMaker.getInternalErrorResponse(new ProductResponse(), e.toString()));
        }
    }

    private ResponseEntity<ProductResponse> getResponseEntity(ProductResponse response) {
        log.info("send response - {}", response.getStatus());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
