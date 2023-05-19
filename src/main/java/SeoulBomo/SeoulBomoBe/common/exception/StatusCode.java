package SeoulBomo.SeoulBomoBe.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
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
    AWS_S3_UPLOAD_FAIL(400, 9040, "AWS S3 upload fail."),
    AWS_S3_DELETE_FAIL(400, 9050, "AWS S3 delete fail."),
    AWS_S3_FILE_SIZE_EXCEEDED(400, 9060, "exceeded file size."),
    PAUSED_ACCOUNT(400, 9070, "paused account error."),
    BANNED_ACCOUNT(400, 9080, "banned account error."),
    PERMANENTLY_BANNED_ACCOUNT(400, 9090, "permanently banned account error."),

    ;
    private int httpCode;
    private int errorCode;
    private String message;
}