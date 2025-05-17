package fmi.sports.tournament.organizer.backend.response;

import fmi.sports.tournament.organizer.backend.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponse {
    public static UserResponseBuilder fromDTO(UserDTO userDTO) {
        return UserResponse.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .birthDate(userDTO.getBirthDate())
                .creationDate(userDTO.getCreationDate())
                .isActive(userDTO.isActive());
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private LocalDate creationDate;
    private boolean isActive;
    private ResponseResult responseResult;
    private String message;
}
