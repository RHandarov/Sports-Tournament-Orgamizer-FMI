package fmi.sports.tournament.organizer.backend.repositories;

import fmi.sports.tournament.organizer.backend.entities.notification.Notification;
import fmi.sports.tournament.organizer.backend.entities.notification.NotificationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification, NotificationId> {
}
