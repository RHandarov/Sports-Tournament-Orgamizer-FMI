package fmi.sports.tournament.organizer.backend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {

    private Long id;
    private String name;
    private String email;
    private Double budget;
    private Integer size;
    private ResponseResult responseResult;
    private String message;
}
