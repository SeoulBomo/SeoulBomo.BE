package SeoulBomo.SeoulBomoBe.domain.childCareInfo.exception;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;

public class ChildCareInfoException extends RuntimeException {
    public StatusCode statusCode;

    public ChildCareInfoException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
