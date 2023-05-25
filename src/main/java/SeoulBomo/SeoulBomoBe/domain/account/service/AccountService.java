package SeoulBomo.SeoulBomoBe.domain.account.service;

import SeoulBomo.SeoulBomoBe.common.exception.StatusCode;
import SeoulBomo.SeoulBomoBe.config.PrincipalDetails;
import SeoulBomo.SeoulBomoBe.config.jwt.TokenProvider;
import SeoulBomo.SeoulBomoBe.domain.account.dto.AccountDto.*;
import SeoulBomo.SeoulBomoBe.domain.account.exception.AccountException;
import SeoulBomo.SeoulBomoBe.domain.account.model.Account;
import SeoulBomo.SeoulBomoBe.domain.account.model.SocialType;
import SeoulBomo.SeoulBomoBe.domain.account.repository.AccountRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static SeoulBomo.SeoulBomoBe.config.security.SecurityUtils.getCurrentAccountEmail;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final TokenProvider tokenProvider;
    private final AccountRepository accountRepository;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String NAVER_USER_INFO_URI;
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URI;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String KAKAO_USER_INFO_URI;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse res) {
        if (loginRequest.socialType().toUpperCase().equals(SocialType.KAKAO.toString())) {
            return kakaoToken(loginRequest.accessToken(), res);
        } else {
            return naverToken(loginRequest.accessToken(), res);
        }
    }

    @Transactional
    public LoginResponse naverToken(String code, HttpServletResponse response) {
        try {
            JSONParser jsonParser = new JSONParser();
            String header = "Bearer " + code;
            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Authorization", header);
            String responseBody = get(NAVER_USER_INFO_URI, requestHeaders);
            System.out.println(responseBody);
            JSONObject parse = (JSONObject) jsonParser.parse(responseBody);

            JSONObject responseParse = (JSONObject) parse.get("response");
            String name = (String) responseParse.get("nickname");
            String email = (String) responseParse.get("email");
            String profileImage = (String) responseParse.get("profile_image");
            Account naverAccount;
            if (!accountRepository.existsByEmail(email)) {
                naverAccount = Account.toEntity(name, email, SocialType.NAVER, profileImage);
                accountRepository.save(naverAccount);
            } else {
                naverAccount = accountRepository.findByEmail(email).get();
            }
            String access_token = tokenProvider.create(new PrincipalDetails(naverAccount));
            response.addHeader("Authorization", "Bearer " + access_token);
            return LoginResponse.of(naverAccount, access_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public LoginResponse kakaoToken(String code, HttpServletResponse res) {
        try {
            String header = "Bearer " + code;
            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Authorization", header);
            String responseBody = get(KAKAO_USER_INFO_URI, requestHeaders);
            System.out.println(responseBody);

            JSONParser jsonParser = new JSONParser();
            JSONObject profile = (JSONObject) jsonParser.parse(responseBody);
            JSONObject properties = (JSONObject) profile.get("properties");
            JSONObject kakao_account = (JSONObject) profile.get("kakao_account");

            String email = (String) kakao_account.get("email");
            String name = (String) properties.get("nickname");
            String profileImage = (String) properties.get("profile_image");
            Account kakaoAccount;

            if (!accountRepository.existsByEmail(email)) {
                kakaoAccount = Account.toEntity(name, email, SocialType.KAKAO, profileImage);
                accountRepository.save(kakaoAccount);
            } else {
                kakaoAccount = accountRepository.findByEmail(email).get();
            }
            String access_token = tokenProvider.create(new PrincipalDetails(kakaoAccount));
            res.setHeader("Authorization", "Bearer " + access_token);
            return LoginResponse.of(kakaoAccount, access_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    public MultiValueMap<String, String> accessTokenParams(String grantType, String clientId, String code, String redirect_uri) {
        MultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add("grant_type", grantType);
        accessTokenParams.add("client_id", clientId);
        accessTokenParams.add("code", code); // 응답으로 받은 코드
        accessTokenParams.add("redirect_uri", redirect_uri); // 응답으로 받은 코드
        return accessTokenParams;
    }

    public void logout(PrincipalDetails principalDetails, HttpServletRequest request, HttpSession session) throws IOException {
        String role = userRepository.findByLoginId(principalDetails.getUsername()).getRole();
        String authorization = request.getHeader("Authorization");
        if(role.equals("ROLE_KAKAO")) {
            String access_token = (String) session.getAttribute("Authorization");
            if (access_token != null && !"".equals(access_token)) {
                HttpURLConnection connect = connect("https://kapi.kakao.com/v1/user/logout");
                connect.setRequestMethod("POST");
                connect.setRequestProperty("Authorization", "Bearer " + access_token);
                readBody(connect.getInputStream());
                session.removeAttribute("access_token");
            }
        }else if(role.equals("ROLE_NAVER")) {
            HttpURLConnection connect = connect("https://nid.naver.com/nidlogin.logout");
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Authorization", "Bearer " + authorization);
            readBody(connect.getInputStream());
        }
    }*/

    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);
        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();
            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    public Account getCurrentAccount() {
        return accountRepository.findByEmail(getCurrentAccountEmail()).orElseThrow(() -> new AccountException(StatusCode.NOT_FOUND_ACCOUNT));
    }
}
