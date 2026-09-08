package com.bhaktiBhraman.app.features.auth.dto;


import lombok.Data;

@Data
public class SignupRequest {

    private String name;
    private String email;
    private String mobile;
    private String address;
    private Double latitude;
    private Double longitude;
    private String password;
    private String confirmPassword;
    private String Role;
}
