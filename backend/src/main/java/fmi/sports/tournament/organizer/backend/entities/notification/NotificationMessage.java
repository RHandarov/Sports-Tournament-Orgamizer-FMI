package fmi.sports.tournament.organizer.backend.entities.notification;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class NotificationMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String messageContent;

    public NotificationMessage(String messageContent) {
        this.messageContent = messageContent;
    }

    public Long getId() {
        return id;
    }

    public String getMessageContent() {
        return messageContent;
    }
}
