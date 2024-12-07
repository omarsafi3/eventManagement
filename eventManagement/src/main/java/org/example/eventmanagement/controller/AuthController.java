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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RefreshTokenService refreshTokenService;



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

        RefreshToken refreshToken = refreshTokenService.findByToken(requestRefreshToken);

        // Check if refreshToken is null
        if (refreshToken == null) {
            throw new TokenRefreshException(requestRefreshToken, "Missing refresh token in database. Please make a new signin request");
        }

        // Verify expiration of the token (you can adjust logic to handle null cases if needed)
        if (refreshTokenService.verifyExpiration(refreshToken) == null) {
            throw new TokenRefreshException(requestRefreshToken, "Token expired.");
        }

        // Get user ID and find user by ID
        long userId = refreshToken.getUserId();
        User user = service.findById(userId);

        if (user == null) {
            throw new TokenRefreshException(requestRefreshToken, "User not found.");
        }

        // Generate a new token and send response
        String token = jwtService.generateToken(user.getEmail());
        Date expiresIn = jwtService.extractExpiration(token);
        AuthResponse authResponse = new AuthResponse(token, expiresIn, requestRefreshToken);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        refreshTokenService.deleteByToken(refreshToken);
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>((User) authentication.getPrincipal(), HttpStatus.OK);
    }


}