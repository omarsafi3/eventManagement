package org.example.eventmanagement.service.impl;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.example.eventmanagement.entity.RefreshToken;
import org.example.eventmanagement.exception.TokenRefreshException;
import org.example.eventmanagement.repository.RefreshTokenRepository;
import org.example.eventmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenService {
    @Value("${security.jwt.refresh-expiration-time}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Long userId) {
        // Ensure user exists before creating a token
        var user = userRepository.findById(userId);

        // Delete any existing tokens for this user
        refreshTokenRepository.deleteByUserId(user.getId());

        // Create a new refresh token
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(user.getId());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.deleteByToken(token.getToken());
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request.");
        }
        return token;
    }

    @Transactional
    public Long deleteByUserId(Long userId) {
        var user = userRepository.findById(userId);
        return refreshTokenRepository.deleteByUser(user);
    }

    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
