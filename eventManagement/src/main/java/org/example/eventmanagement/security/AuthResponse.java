package org.example.eventmanagement.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AuthResponse {
    private String token;
    private Date expiresIn;
    private String refreshToken;

}