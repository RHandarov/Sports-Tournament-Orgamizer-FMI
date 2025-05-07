package fmi.sports.tournament.organizer.backend.entities.auth;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    private Long userId;

    @MapsId
    @OneToOne
    private User user;
    private String sessionId;
    private LocalDateTime expires;

    public Session(User user, LocalDateTime expires) {
        this.user = user;
        this.sessionId = TokenGenerator.generateRandomToken();
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
}
