package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.RefreshToken;

import jakarta.xml.bind.*;
import org.example.eventmanagement.entity.User;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class RefreshTokenRepository {
    private final File file = new File("refreshTokens.xml");

    public RefreshToken save(RefreshToken token) {
        List<RefreshToken> tokens = findAll();
        tokens.add(token);
        writeToFile(tokens);
        return token;
    }

    public Long deleteByUser(User user) {
        List<RefreshToken> tokens = findAll();
        // Filter out tokens that belong to the given user
        List<RefreshToken> updatedTokens = tokens.stream()
                .filter(token -> token.getUserId() != user.getId())
                .collect(Collectors.toList());
        writeToFile(updatedTokens);
        return user.getId();// Save the updated list back to the file
    }

    public void deleteByToken(String token) {
        List<RefreshToken> tokens = findAll();
        // Filter out tokens that do not have the given token
        List<RefreshToken> updatedTokens = tokens.stream()
                .filter(tok -> !tok.getToken().equals(token))
                .collect(Collectors.toList());
        writeToFile(updatedTokens); // Save the updated list back to the file
    }

    public void delete(RefreshToken token) {
        List<RefreshToken> tokens = findAll();
        tokens.remove(token);
        writeToFile(tokens);
    }

    public List<RefreshToken> findAll() {
        if (!file.exists()) return new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(RefreshToken.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (List<RefreshToken>) unmarshaller.unmarshal(file);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public RefreshToken findByToken(String token) {
        List<RefreshToken> tokens = findAll();
        return tokens.stream()
                .filter(tok -> tok.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }

    public void deleteByUserId(long userId) {
        List<RefreshToken> tokens = findAll();
        // Filter out tokens that do not belong to the given userId
        List<RefreshToken> updatedTokens = tokens.stream()
                .filter(token -> token.getUserId() != userId)
                .collect(Collectors.toList());
        writeToFile(updatedTokens); // Save the updated list back to the file
    }

    public void deleteByExpiryDateBefore(Instant now) {
        List<RefreshToken> tokens = findAll();
        // Filter out tokens whose expiry date is before the given instant
        List<RefreshToken> updatedTokens = tokens.stream()
                .filter(token -> token.getExpiryDate().isAfter(now))
                .collect(Collectors.toList());
        writeToFile(updatedTokens); // Save the updated list back to the file
    }

    private void writeToFile(List<RefreshToken> tokens) {
        try {
            JAXBContext context = JAXBContext.newInstance(RefreshToken.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tokens, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
