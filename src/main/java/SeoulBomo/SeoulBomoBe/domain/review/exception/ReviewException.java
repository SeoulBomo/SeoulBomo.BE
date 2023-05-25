package SeoulBomo.SeoulBomoBe.domain.review.exception;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;

public class ReviewException extends RuntimeException {

    public StatusCode statusCode;

    public ReviewException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
