package ali.su.cft2j02.product;

import ali.su.cft2j02.entity.ProductClass;
import ali.su.cft2j02.entity.ProductRegisterType;
import ali.su.cft2j02.mapping.ProductRequestMap;
import ali.su.cft2j02.mapping.ProductRequestToProductMapper;
import ali.su.cft2j02.prodregister.ProductRegisterService;
import ali.su.cft2j02.repo.ProductRegisterTypeRepository;
import ali.su.cft2j02.entity.Product;
import ali.su.cft2j02.product.messages.ProductRequest;
import ali.su.cft2j02.product.messages.ProductResponse;
import ali.su.cft2j02.repo.ProductClassRepository;
import ali.su.cft2j02.repo.ProductRepository;
import ali.su.cft2j02.utils.ResponseMaker;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис, создающий экземпляр продукта
 */
@Service
@Setter
@AllArgsConstructor
@Slf4j
public class ProductService {
    private ProductRepository productRepo;
    private ProductClassRepository productClassRepo;
    private ProductRegisterTypeRepository productRegisterTypeRepo;
    private ProductRequestToProductMapper productRequestToProductMapper;
    private ProductRegisterService productRegisterService;

    private static final String CLIENT_ACCOUNT_TYPE = "Клиентский";

    public ProductResponse validateRequest(ProductRequest request) {
        // Проверка Request.Body на обязательность
        if (request == null) {
            String info = "Request.Body не заполнено";
            log.info(info);
            return ResponseMaker.getBadResponse(new ProductResponse(), info);
        }

        // Проверка заполненности обязательных полей
        if (!request.isValid()) {
            String info = String.format("Значение обязательного параметра %s не заполнено.", request.getFailField());
            log.info(info);
            return ResponseMaker.getBadResponse(new ProductResponse(), info);
        }

        return null;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        // Проверка таблицы ЭП на дубли
        List<Product> list = productRepo.findProductsByNumber(request.getContractNumber());
        if (!list.isEmpty()) {
            String info = String.format("Параметр ContractNumber № договора %s уже существует для ЭП с ИД %d",
                                        request.getContractNumber(), list.get(0).getId());
            log.info(info);
            return ResponseMaker.getBadResponse(new ProductResponse(), info);
        }

        ProductClass productClass = productClassRepo.findProductClassByValue(request.getProductCode());
        if (productClass == null) {
            String info = String.format("КодПродукта %s не найден в Каталоге продуктов", request.getProductCode());
            log.info(info);
            return ResponseMaker.getNotFoundResponse(new ProductResponse(), info);
        }

        // Поиск списка типов регистра по коду продукта
        List<ProductRegisterType> prodRegTypeList =
                productRegisterTypeRepo.findProductRegisterTypesByProductClassCodeAndAccountType(
                        request.getProductCode(), CLIENT_ACCOUNT_TYPE);
        if (prodRegTypeList.isEmpty()) {
            String info = String.format("Список ТипРегистра не найден в Каталоге типа регистра по КодПродукта %s",
                                        request.getProductCode());
            log.info(info);
            return ResponseMaker.getNotFoundResponse(new ProductResponse(), info);
        }

        // Подготовка к созданию ЭП
        ProductRequestMap productRequestMap = ProductRequestMap.builder()
                .productRequest(request)
                .productClass(productClass)
                .build();

        // Добавить запись в таблицу tpp_product
        Product product = productRequestToProductMapper.map(productRequestMap);
        product = productRepo.save(product);

        // Создать ПР
        request.setInstanceId(product.getId());
        List<Long> productRegisters = productRegisterService.createProductRegisters(prodRegTypeList, request);

        ProductResponse response = ResponseMaker.getOkResponse(new ProductResponse(), product.getId().toString());
        response.setRegisterId(productRegisters.stream().map(Object::toString).toList());
        log.info("created Product, response = {}", response);
        return response;
    }
}
