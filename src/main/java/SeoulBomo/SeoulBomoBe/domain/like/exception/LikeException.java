package SeoulBomo.SeoulBomoBe.domain.like.exception;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;

public class LikeException extends RuntimeException{
    public StatusCode statusCode;

    public LikeException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
