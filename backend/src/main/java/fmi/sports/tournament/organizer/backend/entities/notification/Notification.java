package fmi.sports.tournament.organizer.backend.entities.notification;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "message_id")
    private NotificationMessage message;
    private LocalDateTime creationTime;
    private boolean isRead;

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
}
