package org.example.eventmanagement.controller;

import org.example.eventmanagement.entity.*;
import org.example.eventmanagement.exception.TokenRefreshException;
import org.example.eventmanagement.entity.RefreshToken;
import org.example.eventmanagement.security.AuthRequest;
import org.example.eventmanagement.security.AuthResponse;
import org.example.eventmanagement.security.TokenRefreshRequest;
import org.example.eventmanagement.security.TokenRefreshResponse;
import org.example.eventmanagement.service.impl.JwtService;
import org.example.eventmanagement.service.impl.RefreshTokenService;
import org.example.eventmanagement.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    AuthController(UserService service, JwtService jwtService, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody User user) {
        String responseMessage = service.addUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", responseMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            Date expiresIn = jwtService.extractExpiration(token);
            User userDetails = (User) authentication.getPrincipal();

            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
            AuthResponse authResponse = new AuthResponse(token,expiresIn , refreshToken.getToken());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(refreshToken -> {
                    // Delete the old refresh token
                    refreshTokenService.deleteByToken(requestRefreshToken);

                    // Generate a new refresh token
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(refreshToken.getUser().getId());

                    // Generate a new access token
                    String newAccessToken = jwtService.generateToken(refreshToken.getUser().getEmail());

                    // Return the new access token and new refresh token in the response
                    return new ResponseEntity<>(new TokenRefreshResponse(newAccessToken, newRefreshToken.getToken()), HttpStatus.OK);
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        refreshTokenService.deleteByToken(refreshToken);
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }


}