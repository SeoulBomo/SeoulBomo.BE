package SeoulBomo.SeoulBomoBe.config.jwt;

import SeoulBomo.SeoulBomoBe.config.PrincipalDetailService;
import SeoulBomo.SeoulBomoBe.config.PrincipalDetails;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final PrincipalDetailService principalDetailService;

    @Value("${jwt.access-duration}")
    public int accessDuration;
    @Value("${jwt.refresh-duration}")
    public int refreshDuration;

    public String create(PrincipalDetails principalDetails) {
        return JWT.create()
                .withSubject("seoulbomo")
                .withExpiresAt(new Date(System.currentTimeMillis() + (accessDuration)))
                .withClaim("id", principalDetails.getAccount().getId())
                .withClaim("email", principalDetails.getAccount().getEmail())
                .sign(Algorithm.HMAC512("seoulbomo"));
    }

    public String resolveToken(HttpServletRequest request) {
        String rawToken = request.getHeader("Authorization");
        if (rawToken != null && rawToken.startsWith("Bearer "))
            return rawToken.replace("Bearer ", "");
        else return null;
    }

    /*private String createRefreshToken(PrincipalDetails principalDetails) {
        PrincipalDetails principal = principalDetails;
        return JWT.create()
                .withSubject("seoulbomo")
                .withExpiresAt(new Date(System.currentTimeMillis() + (refreshDuration)))
                .withClaim("id", principal.getAccount().getId())
                .withClaim("email", principal.getAccount().getEmail())
                .withClaim("tokenType", "refresh")
                .sign(Algorithm.HMAC512("seoulbomo"));
    }*/

    public boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512("seoulbomo"); // 비밀키 설정
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("seoulbomo") // 토큰 발급자 설정
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Authentication getAuthentication(String accessToken) {
        String email = JWT.decode(accessToken).getClaim("email").asString();
        UserDetails userDetails = principalDetailService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
