package org.example.eventmanagement.repository;

import org.example.eventmanagement.entity.RefreshToken;

import jakarta.xml.bind.*;
import org.example.eventmanagement.entity.User;
import org.example.eventmanagement.wrapper.RefreshTokenListWrapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class RefreshTokenRepository {
    private final File file = new File("refreshTokens.xml");
    private final Object lock = new Object();

    public RefreshToken save(RefreshToken token) {
        synchronized (lock) {
            List<RefreshToken> tokens = findAll();
            tokens.add(token);
            writeToFile(tokens);
            return token;
        }
    }

    public Long deleteByUser(User user) {
        synchronized (lock) {
            List<RefreshToken> tokens = findAll();
            List<RefreshToken> updatedTokens = tokens.stream()
                    .filter(token -> token.getUserId() != 0 && token.getUserId() != user.getId())
                    .collect(Collectors.toList());
            writeToFile(updatedTokens);
            return user.getId();
        }
    }
    public void deleteByExpiryDateBefore( Instant expiryDate) {
        synchronized (lock) {
            List<RefreshToken> tokens = findAll();
            List<RefreshToken> updatedTokens = tokens.stream()
                    .filter(token -> token.getExpiryDate().isBefore(expiryDate))
                    .collect(Collectors.toList());
            writeToFile(updatedTokens);
        }

    }
    public Long deleteByUserId(Long userId) {
        synchronized (lock) {
            List<RefreshToken> tokens = findAll();
            List<RefreshToken> updatedTokens = tokens.stream()
                    .filter(token -> token.getUserId() != 0 && token.getUserId() != userId)
                    .collect(Collectors.toList());
            writeToFile(updatedTokens);
            return userId;
        }
    }
    public RefreshToken findByToken(String token) {
        List<RefreshToken> tokens = findAll();
        return tokens.stream()
                .filter(tok -> tok.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }

    public void deleteByToken(String token) {
        synchronized (lock) {
            List<RefreshToken> tokens = findAll();
            List<RefreshToken> updatedTokens = tokens.stream()
                    .filter(tok -> !tok.getToken().equals(token))
                    .collect(Collectors.toList());
            writeToFile(updatedTokens);
        }
    }

    public List<RefreshToken> findAll() {
        synchronized (lock) {
            if (!file.exists()) {
                writeToFile(new ArrayList<>());
                return new ArrayList<>();
            }
            try {
                JAXBContext context = JAXBContext.newInstance(RefreshTokenListWrapper.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                RefreshTokenListWrapper wrapper = (RefreshTokenListWrapper) unmarshaller.unmarshal(file);
                return wrapper.getTokens();
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
    }

    private void writeToFile(List<RefreshToken> tokens) {
        synchronized (lock) {
            try {
                JAXBContext context = JAXBContext.newInstance(RefreshTokenListWrapper.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                RefreshTokenListWrapper wrapper = new RefreshTokenListWrapper();
                wrapper.setTokens(tokens);
                marshaller.marshal(wrapper, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
