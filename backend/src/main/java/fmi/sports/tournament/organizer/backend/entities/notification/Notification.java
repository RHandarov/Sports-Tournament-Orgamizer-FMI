package fmi.sports.tournament.organizer.backend.entities.notification;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@IdClass(NotificationId.class)
public class Notification {
    @Id
    private Long userId;

    @Id
    private Long messageId;

    @MapsId("userId")
    @ManyToOne
    private User user;

    @MapsId("messageId")
    @ManyToOne
    private NotificationMessage message;

    @Id
    private LocalDateTime creationTime;
    private boolean isRead;

    public Notification() {

    }

    public Notification(User user, NotificationMessage message) {
        this.user = user;
        this.message = message;
        this.creationTime = LocalDateTime.now();
        this.isRead = false;
    }

    public User getUser() {
        return user;
    }

    public NotificationMessage getMessage() {
        return message;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMessage(NotificationMessage message) {
        this.message = message;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
