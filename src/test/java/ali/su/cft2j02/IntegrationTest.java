package ali.su.cft2j02;

import ali.su.cft2j02.prodregister.ProductRegisterController;
import ali.su.cft2j02.prodregister.messages.ProductRegisterRequest;
import ali.su.cft2j02.prodregister.messages.ProductRegisterResponse;
import ali.su.cft2j02.product.ProductController;
import ali.su.cft2j02.product.messages.InstanceAgreement;
import ali.su.cft2j02.product.messages.ProductRequest;
import ali.su.cft2j02.product.messages.ProductResponse;
import ali.su.cft2j02.repo.AgreementRepository;
import ali.su.cft2j02.repo.ProductRegisterRepository;
import ali.su.cft2j02.repo.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;

@SpringBootTest(classes = {Starter.class})
public class IntegrationTest {
    @Autowired
    private ProductController productController;
    @Autowired
    private ProductRegisterController productRegisterController;


    @BeforeEach
    public void beforeEachTests(
            @Autowired AgreementRepository agreementRepository,
            @Autowired ProductRegisterRepository productRegisterRepository,
            @Autowired ProductRepository productRepository
                             ) {
        agreementRepository.deleteAll();
        productRegisterRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Создание Экземпляра Продукта")
    public void createProduct() {
        final ResponseEntity<ProductResponse> response = productController.postRequest(getRequest("11350"));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getInstanceId());
        Assertions.assertEquals(1, response.getBody().getRegisterId().size());
    }

    @Test
    @DisplayName("Создание доп соглашения к договору")
    public void createAgreement() {
        // создаем сам договор
        ResponseEntity<ProductResponse> response = productController.postRequest(getRequest("11352"));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // создаем доп соглашение к договору
        final ProductRequest requestAgr = new ProductRequest();
        final Long product_id = Long.valueOf(response.getBody().getInstanceId());
        requestAgr.setInstanceId(product_id);
        requestAgr.setContractId(product_id);

        final InstanceAgreement instanceAgreement = new InstanceAgreement();
        instanceAgreement.setNumber("10002");
        instanceAgreement.setOpeningDate(new Date());
        requestAgr.setInstanceAgreements(Collections.singletonList(instanceAgreement));

        response = productController.postRequest(requestAgr);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getInstanceId());
        Assertions.assertEquals(1, response.getBody().getSupplementaryAgreementId().size());
    }

    @Test
    @DisplayName("Создание Продуктового регистра")
    public void createProductRegister() {
        // создаем сам договор
        final ResponseEntity<ProductResponse> response = productController.postRequest(getRequest("11354"));

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        // создаем продуктовый регистр к договору
        final Long product_id = Long.valueOf(response.getBody().getInstanceId());
        final ProductRegisterRequest productRegisterRequest = new ProductRegisterRequest();
        productRegisterRequest.setInstanceId(product_id);
        productRegisterRequest.setRegisterTypeCode("03.012.002_47534_ComSoLd");
        productRegisterRequest.setAccountType("Внутрибанковский");
        productRegisterRequest.setCurrencyCode("800");
        productRegisterRequest.setPriorityCode("00");

        final ResponseEntity<ProductRegisterResponse> registerResponse = productRegisterController.postRequest(productRegisterRequest);

        Assertions.assertEquals(HttpStatus.OK, registerResponse.getStatusCode());
        Assertions.assertNotNull(registerResponse.getBody());
        Assertions.assertNotNull(registerResponse.getBody().getRegisterId());
    }

    private ProductRequest getRequest(String contractNumber) {
        final ProductRequest request = new ProductRequest();
        request.setProductType("договор");
        request.setProductCode("03.012.002");
        request.setRegisterType("03.012.002_47533_ComSoLd");
        request.setMdmCode("15");
        request.setContractNumber(contractNumber);
        request.setContractDate(new Date());
        request.setPriority(5L);
        request.setBranchCode("0022");
        request.setIsoCurrencyCode("800");
        request.setUrgencyCode("00");
        return request;
    }
}