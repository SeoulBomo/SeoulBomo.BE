package SeoulBomo.SeoulBomoBe.domain.childCenterInfo.exception;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;

public class ChildCenterInfoException extends RuntimeException {
    public StatusCode statusCode;

    public ChildCenterInfoException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
