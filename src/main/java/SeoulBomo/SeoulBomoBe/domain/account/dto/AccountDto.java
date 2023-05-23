package SeoulBomo.SeoulBomoBe.domain.account.dto;

import lombok.Builder;

public class AccountDto {
    public record LoginRequest(
            String socialType,
            String accessToken
    ) {
    }

    public record LoginResponse(
            String accessToken
    ) {
        @Builder
        public LoginResponse {
        }
        public static LoginResponse of(String accessToken) {
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .build();
        }
    }
}
