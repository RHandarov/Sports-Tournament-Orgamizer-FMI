package fmi.sports.tournament.organizer.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewNotificationDTO {
    // TODO: Add validations

    private Long receiverId;
    private String message;
}
