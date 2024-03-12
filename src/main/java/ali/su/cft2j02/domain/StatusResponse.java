package ali.su.cft2j02.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class StatusResponse {
    @JsonIgnore
    private HttpStatus status;

    public abstract void setTextResponse(String text);
}