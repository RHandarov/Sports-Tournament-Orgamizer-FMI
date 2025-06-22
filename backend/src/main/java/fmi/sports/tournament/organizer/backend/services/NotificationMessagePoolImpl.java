package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.entities.notification.NotificationMessage;
import fmi.sports.tournament.organizer.backend.repositories.NotificationMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationMessagePoolImpl implements NotificationMessagePool {
    private final NotificationMessageRepository notificationMessageRepository;

    @Autowired
    public NotificationMessagePoolImpl(NotificationMessageRepository notificationMessageRepository) {
        this.notificationMessageRepository = notificationMessageRepository;
    }

    @Override
    public NotificationMessage getNotificationMessage(String message) {
        Optional<NotificationMessage> notificationMessage =
                notificationMessageRepository.findByMessageContent(message);
        return notificationMessage.orElseGet(() -> notificationMessageRepository.save(new NotificationMessage(message)));
    }
}
