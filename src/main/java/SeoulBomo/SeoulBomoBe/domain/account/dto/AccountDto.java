package SeoulBomo.SeoulBomoBe.domain.account.dto;

import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import lombok.Builder;

public class AccountDto {
    public record LoginRequest(
            String socialType,
            String accessToken
    ) {
    }

    public record LoginResponse(
            long id,
            String accessToken,
            String socialType,
            String profileImage,
            String name
    ) {
        @Builder
        public LoginResponse {
        }
        public static LoginResponse of(Account account, String accessToken) {
            return LoginResponse.builder()
                    .id(account.getId())
                    .socialType(account.getSocialType().toString())
                    .profileImage(account.getImage())
                    .name(account.getName())
                    .accessToken(accessToken)
                    .build();
        }
    }
}
