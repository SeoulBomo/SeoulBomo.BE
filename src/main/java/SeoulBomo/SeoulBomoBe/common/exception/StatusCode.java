package SeoulBomo.SeoulBomoBe.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    CLIENT_ERROR(400, 1000, "입력 오류입니다."),
    NOT_FOUND_USER(0,0, ""),
    NOT_FOUND_ARTICLE(0,0, ""),
    NOT_FOUND_(0,0, "");

    private int httpCode;
    private int errorCode;
    private String message;

    StatusCode(int httpCode, int errorCode, String message) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}