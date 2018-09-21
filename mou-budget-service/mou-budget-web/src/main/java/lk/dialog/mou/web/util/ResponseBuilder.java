package lk.dialog.mou.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Created by Aux072 on 20/09/2018.
 */
public class ResponseBuilder {
    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> APIResponse<T> success(T data, Map<String, Object> additionalParams) {
        APIResponse<T> response = success(data);
        response.setAdditionalParams(additionalParams);
        return response;
    }

    public static <T> APIResponse<T> forbidden(T data) {
        return new APIResponse<>(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), data);
    }

    public static <T> APIResponse<T> failed(T data, Exception ex) {
        return new APIResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), data);
    }

    public static <T> APIResponse<T> failed(T data, Exception ex, Map<String, Object> additionalParams) {
        APIResponse<T> failedResponse = failed(data, ex);
        failedResponse.setAdditionalParams(additionalParams);
        return failedResponse;
    }

    public static ResponseEntity<APIResponse> build(APIResponse apiResponse) {
        if (apiResponse.getStatusCode() == HttpStatus.OK.value()) {
            return ResponseEntity.ok(apiResponse);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(apiResponse);
    }
}
