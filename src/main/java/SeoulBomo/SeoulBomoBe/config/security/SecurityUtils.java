package SeoulBomo.SeoulBomoBe.config.security;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.domain.account.exception.AccountException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtils {
    public static String getCurrentAccountEmail() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new AccountException(StatusCode.NOT_FOUND_ACCOUNT);
        }
        return authentication.getName();
    }
}
