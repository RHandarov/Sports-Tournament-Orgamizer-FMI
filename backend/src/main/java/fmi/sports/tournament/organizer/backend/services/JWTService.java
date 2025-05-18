package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.configs.JWTConfig;
import fmi.sports.tournament.organizer.backend.dtos.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JWTService {
    private final SecretKey secretKey;
    private final JWTConfig jwtConfig;

    @Autowired
    public JWTService(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        secretKey = Keys.hmacShaKeyFor(jwtConfig.getSigningSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDTO userDTO) {
        Date issueDate = new Date();
        return Jwts.builder()
                .subject(userDTO.getEmail())
                .issuedAt(issueDate)
                .expiration(new Date(issueDate.getTime() + jwtConfig.getExpirationImNs()))
                .signWith(secretKey)
                .compact();
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parse(token);
        } catch (Exception _) {
            return false;
        }

        return true;
    }

    public LocalDateTime getExpirationTime(String token) {
        return null; // TODO: to be implemented
    }
}
