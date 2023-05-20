package SeoulBomo.SeoulBomoBe.common.exception;

import SeoulBomo.SeoulBomoBe.common.dto.ApiErrorResponse;
import SeoulBomo.SeoulBomoBe.domain.childCareInfo.exception.ChildCareInfoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChildCareInfoException.class)
    public ResponseEntity<ApiErrorResponse> handle(ChildCareInfoException ex) {
        log.error("handleException {}", ex.getMessage());
        ApiErrorResponse response = ApiErrorResponse.builder()
                .httpCode(ex.statusCode.getHttpCode())
                .errorCode(ex.statusCode.getErrorCode())
                .message(ex.statusCode.getMessage())
                .build();
        return ResponseEntity.status(ex.statusCode.getHttpCode()).body(response);
    }
}
