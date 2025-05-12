package fmi.sports.tournament.organizer.backend.entities.notification;

import java.io.Serializable;

public class NotificationId implements Serializable {
    private Long userId;
    private Long messageId;

    public Long getUserId() {
        return userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}
