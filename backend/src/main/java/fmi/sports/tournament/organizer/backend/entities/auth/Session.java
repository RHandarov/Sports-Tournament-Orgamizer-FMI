package fmi.sports.tournament.organizer.backend.entities.auth;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {
    private static final int TOKEN_LENGTH = 120;

    @Id
    private Long userId;

    @MapsId
    @OneToOne
    private User user;
    private String sessionId;
    private LocalDateTime expires;

    public Session() {

    }

    public Session(User user, LocalDateTime expires) {
        this.user = user;
        this.sessionId = TokenGenerator.generateRandomToken(TOKEN_LENGTH);
        this.expires = expires;
    }

    public User getUser() {
        return user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }
}
