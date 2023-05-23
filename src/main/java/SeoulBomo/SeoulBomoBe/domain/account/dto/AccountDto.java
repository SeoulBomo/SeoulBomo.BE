package SeoulBomo.SeoulBomoBe.domain.account.dto;

import lombok.Builder;

public class AccountDto {
    public record LoginRequest(
            String socialType,
            String accessToken
    ) {
    }

    public record LoginResponse(
            long id,
            String accessToken
    ) {
        @Builder
        public LoginResponse {
        }
        public static LoginResponse of(Long id, String accessToken) {
            return LoginResponse.builder()
                    .id(id)
                    .accessToken(accessToken)
                    .build();
        }
    }
}
