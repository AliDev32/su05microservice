package ali.su.cft2j02.utils;

import lombok.Data;

import java.util.Date;

@Data
public abstract class RequestValidator {
    private String failField;
    public abstract boolean isValid();

    public static boolean isFailField(String value, Makeable func) {
        if (value == null || value.isEmpty()) {
            func.make();
            return true;
        }
        return false;
    }
    public static boolean isFailField(Date value, Makeable func) {
        if (value == null) {
            func.make();
            return true;
        }
        return false;
    }
    public static boolean isFailField(Long value, Makeable func) {
        if (value == null) {
            func.make();
            return true;
        }
        return false;
    }
}
