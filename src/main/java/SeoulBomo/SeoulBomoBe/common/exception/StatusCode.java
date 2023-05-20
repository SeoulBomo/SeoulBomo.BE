package SeoulBomo.SeoulBomoBe.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum StatusCode {
    // ACCOUNT 1000번대
    NOT_FOUND_ACCOUNT(404, 1000, "not found account error."),

    // CHILDCARE 2000번대
    NOT_FOUND_CHILDCARE(404, 2000, "not found child care info error."),

    // CHILD CENTER 3000번대
    NOT_FOUND_CHILDCENTER(404, 3000, "not found child center error."),

    // REVIEW 4000번대
    NOT_FOUND_REVIEW(404, 4000, "not found review error."),

    // LIKE 5000번대
    NOT_FOUND_LIKE(404, 5000, "not found like error."),

    // COMMON
    COMMON_BAD_REQUEST(400, 9000, ""),
    INVALID_INPUT_VALUE(400, 9010, "invalid input value."),
    METHOD_NOT_ALLOWED(405, 9020, "method not allowed."),
    HTTP_CLIENT_ERROR(400, 9030, "http client error."),


    ;
    private int httpCode;
    private int errorCode;
    private String message;
  
    StatusCode(int httpCode, int errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}