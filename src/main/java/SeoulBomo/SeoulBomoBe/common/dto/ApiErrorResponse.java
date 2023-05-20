package SeoulBomo.SeoulBomoBe.common.dto;

import lombok.*;

public record ApiErrorResponse(
        int httpCode,
        int errorCode,
        String message
) {
    @Builder
    public ApiErrorResponse {
    }
}