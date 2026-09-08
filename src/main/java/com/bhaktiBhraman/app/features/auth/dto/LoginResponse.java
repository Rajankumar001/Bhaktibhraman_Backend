package com.bhaktiBhraman.app.features.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private  String name;
    private String email;
    private String role;
    private String token;
}
