package SeoulBomo.SeoulBomoBe.config.security;

import SeoulBomo.SeoulBomoBe.config.jwt.JwtAuthenticationEntryPoint;
import SeoulBomo.SeoulBomoBe.config.jwt.JwtSecurityConfig;
import SeoulBomo.SeoulBomoBe.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final TokenProvider tokenProvider;

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .shouldFilterAllDispatcherTypes(false)
                        .requestMatchers("/**").permitAll()
                        //.requestMatchers("api/v1/search/**", "api/v1/child-center-info/**", "api/v1/child-care-info/**", "/api/v1/accounts/oauth/**").permitAll()
                        .anyRequest().authenticated())
                .apply(new JwtSecurityConfig(tokenProvider))
                .and()
                .build();
    }
}
