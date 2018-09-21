package lk.dialog.mou.web.util;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Aux072 on 20/09/2018.
 */
public class APIResponse<T> {
    private int statusCode;
    private T data;
    private String message;
    private Map<String, Object> additionalParams = Collections.EMPTY_MAP;

    protected APIResponse() {
    }

    public APIResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }

    public Map<String, Object> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Map<String, Object> additionalParams) {
        this.additionalParams = additionalParams;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "statusCode=" + statusCode +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
