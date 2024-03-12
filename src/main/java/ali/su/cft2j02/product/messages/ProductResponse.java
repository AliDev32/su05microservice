package ali.su.cft2j02.product.messages;

import ali.su.cft2j02.domain.StatusResponse;
import lombok.Data;

import java.util.List;

/**
 * Ответ на запрос создания экземпляра Продукта
 */
@Data
public class ProductResponse extends StatusResponse {
    /**
     * Идентификатор экземпляра продукта
     */
    private String instanceId;
    /**
     * Массив идентификаторов продуктовых регистров
     */
    private List<String> registerId;
    /**
     * Массив доп. соглашений
     */
    private List<String> supplementaryAgreementId;

    @Override
    public void setTextResponse(String text) {
        setInstanceId(text);
    }
}