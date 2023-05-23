package SeoulBomo.SeoulBomoBe.domain.account.exception;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;

public class AccountException extends RuntimeException {
    public StatusCode statusCode;

    public AccountException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
}
