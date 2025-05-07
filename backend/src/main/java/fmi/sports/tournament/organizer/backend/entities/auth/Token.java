package fmi.sports.tournament.organizer.backend.entities.auth;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    private Long userId;

    @MapsId
    @OneToOne
    private User user;

    private String token;
    private LocalDateTime expires;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    public Token(User user, LocalDateTime expires, TokenType type) {
        this.user = user;
        this.token = TokenGenerator.generateRandomToken();
        this.expires = expires;
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public TokenType getType() {
        return type;
    }
}
