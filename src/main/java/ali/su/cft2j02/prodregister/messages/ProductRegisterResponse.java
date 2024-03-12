package ali.su.cft2j02.prodregister.messages;

import ali.su.cft2j02.domain.StatusResponse;
import lombok.Data;

/**
 * Ответ на запрос создания Продуктового регистра
 */
@Data
public class ProductRegisterResponse extends StatusResponse {
    /** Идентификатор продуктового регистра */
    private String registerId;

    @Override
    public void setTextResponse(String text) {
        setRegisterId(text);
    }
}