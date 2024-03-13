package ali.su.cft2j02.prodregister;

import ali.su.cft2j02.prodregister.messages.ProductRegisterRequest;
import ali.su.cft2j02.prodregister.messages.ProductRegisterResponse;
import ali.su.cft2j02.utils.ResponseMaker;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping(path = "${rest.account-endpoint}", produces = "application/json")
public class ProductRegisterController {
    private ProductRegisterService productRegisterService;

    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<ProductRegisterResponse> postRequest(@RequestBody ProductRegisterRequest request) {
        log.info("get post request - {}", request);
        try {
            // Проверка Request
            ProductRegisterResponse response = productRegisterService.validateRequest(request);
            if (response != null) {
                return getResponseEntity(response);
            }

            // Создание нового продуктового регистра
            return getResponseEntity(productRegisterService.createProductRegister(request));
        }
        catch (Exception e) {
            log.info(e.toString());
            return getResponseEntity(
                    ResponseMaker.getInternalErrorResponse(new ProductRegisterResponse(), e.toString()));
        }
    }

    private ResponseEntity<ProductRegisterResponse> getResponseEntity(ProductRegisterResponse response) {
        log.info("send response - {}", response.getStatus());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
