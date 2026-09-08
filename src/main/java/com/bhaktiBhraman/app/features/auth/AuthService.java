package com.bhaktiBhraman.app.features.auth;

import com.bhaktiBhraman.app.features.auth.dto.LoginRequest;
import com.bhaktiBhraman.app.features.auth.dto.SignupRequest;
import com.bhaktiBhraman.app.features.user.UserEntity;
import com.bhaktiBhraman.app.features.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 🔐 SIGNUP
    public String signup(SignupRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // 👉 DTO → Entity conversion (IMPORTANT)
        UserEntity user = UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .Role(request.getRole())
                .longitude(request.getLongitude())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔐 LOGIN
    public UserEntity login(LoginRequest request) {

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
