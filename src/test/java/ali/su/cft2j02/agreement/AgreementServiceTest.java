package ali.su.cft2j02.agreement;

import ali.su.cft2j02.entity.Agreement;
import ali.su.cft2j02.entity.Product;
import ali.su.cft2j02.product.messages.InstanceAgreement;
import ali.su.cft2j02.product.messages.ProductRequest;
import ali.su.cft2j02.product.messages.ProductResponse;
import ali.su.cft2j02.repo.AgreementRepository;
import ali.su.cft2j02.repo.ProductRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgreementServiceTest {
    private final AgreementService agreementService;
    private final ProductRepository productRepoMock;
    private final AgreementRepository agreementRepoMock;

    public AgreementServiceTest() {
        productRepoMock = Mockito.mock(ProductRepository.class);
        agreementRepoMock = Mockito.mock(AgreementRepository.class);
        agreementService = new AgreementService(productRepoMock, agreementRepoMock);

        agreementService.setProductRepo(productRepoMock);
        agreementService.setAgreementRepo(agreementRepoMock);
    }

    @BeforeEach
    void initEach() {
        Mockito.reset(productRepoMock, agreementRepoMock);
    }

    @Test
    @DisplayName("Ошибка - договор с ID не существует")
    public void error_notExistProduct() {
        final ProductRequest request = new ProductRequest();
        request.setContractId(1L);

        Mockito.when(productRepoMock.findById(Mockito.any())).thenReturn(Optional.empty());

        final ProductResponse response = agreementService.createAgreement(request);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    @Test
    @DisplayName("Ошибка - доп соглашение с ID уже существует")
    public void error_existAgreement() {
        final ProductRequest request = new ProductRequest();
        request.setContractId(1L);
        final InstanceAgreement instanceAgreement = new InstanceAgreement();
        instanceAgreement.setNumber("1023");
        request.setInstanceAgreements(List.of(instanceAgreement));

        Mockito.when(productRepoMock.findById(Mockito.any())).thenReturn(Optional.of(new Product()));

        final Agreement agreement = new Agreement();
        agreement.setProductId(111L);
        Mockito.when(agreementRepoMock.findAgreementsByNumber(Mockito.any())).thenReturn(List.of(agreement));

        final ProductResponse response = agreementService.createAgreement(request);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    @DisplayName("Ошибка - в массиве доп соглашений есть дубли")
    public void error_hasDublicates() {
        final ProductRequest request = new ProductRequest();
        request.setContractId(1L);

        final InstanceAgreement instanceAgreement = new InstanceAgreement();
        instanceAgreement.setNumber("1023");
        request.setInstanceAgreements(List.of(instanceAgreement, instanceAgreement));

        Mockito.when(productRepoMock.findById(Mockito.any())).thenReturn(Optional.of(new Product()));
        Mockito.when(agreementRepoMock.findAgreementsByNumber(Mockito.any())).thenReturn(Collections.emptyList());

        final ProductResponse response = agreementService.createAgreement(request);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    @DisplayName("Успех - создание нового доп соглашения")
    public void success_createAgreement() {
        final ProductRequest request = new ProductRequest();
        request.setContractId(1L);

        final InstanceAgreement instanceAgreement = new InstanceAgreement();
        instanceAgreement.setNumber("1023");
        request.setInstanceAgreements(List.of(instanceAgreement));

        final Agreement agreement = new Agreement();
        agreement.setNumber(instanceAgreement.getNumber());
        agreement.setId(88L);

        final Product product = new Product();
        product.setId(99L);

        Mockito.when(productRepoMock.findById(Mockito.any())).thenReturn(Optional.of(product));
        Mockito.when(agreementRepoMock.findAgreementsByNumber(Mockito.any())).thenReturn(Collections.emptyList());
        Mockito.when(agreementRepoMock.save(Mockito.any())).thenReturn(agreement);

        final ProductResponse response = agreementService.createAgreement(request);
        Assertions.assertEquals(HttpStatus.OK, response.getStatus());
    }
}