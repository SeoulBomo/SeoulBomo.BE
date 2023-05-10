package SeoulBomo.SeoulBomoBe.domain.account.model;

import org.springframework.http.HttpMethod;

public enum SocialType {
    KAKAO(
            "kakao",
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET
    ),
    Naver(
            "naver",
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET
    );

    private String socialName;
    private String socialUrl;
    private HttpMethod httpMethod;

    SocialType(String socialName, String socialUrl, HttpMethod httpMethod) {
        this.socialName = socialName;
        this.socialUrl = socialUrl;
        this.httpMethod = httpMethod;
    }
}
