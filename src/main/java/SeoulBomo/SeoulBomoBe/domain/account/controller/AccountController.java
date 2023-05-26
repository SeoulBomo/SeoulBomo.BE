package SeoulBomo.SeoulBomoBe.domain.account.controller;

import SeoulBomo.SeoulBomoBe.domain.account.dto.AccountDto.*;
import SeoulBomo.SeoulBomoBe.domain.account.service.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService loginService;

    @CrossOrigin("localhost:3000")
    @PostMapping("/oauth/login")
    public LoginResponse login(@RequestBody LoginRequest LoginRequest, HttpServletResponse res) {
        return loginService.login(LoginRequest, res);
    }
}