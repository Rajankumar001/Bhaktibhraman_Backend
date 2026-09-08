package com.bhaktiBhraman.app.features.auth;

import com.bhaktiBhraman.app.features.auth.dto.LoginRequest;
import com.bhaktiBhraman.app.features.auth.dto.LoginResponse;
import com.bhaktiBhraman.app.features.auth.dto.SignupRequest;
import com.bhaktiBhraman.app.security.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JWTUtils jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        var user = authService.login(request);
        String name= user.getName();
        String email=user.getEmail();
        String role= user.getRole();
        String token=jwtUtil.generateToken(user.getEmail());
        return new LoginResponse(
                name,
                email,
                role,
                token
        );


    }
}
