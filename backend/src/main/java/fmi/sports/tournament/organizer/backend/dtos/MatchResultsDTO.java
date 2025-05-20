package fmi.sports.tournament.organizer.backend.dtos;

import jakarta.validation.constraints.Positive;

public class MatchResultsDTO {

    @Positive(message = "Team 1 points must be a positive number")
    private Integer team1Points;

    @Positive(message = "Team 2 points must be a positive number")
    private Integer team2Points;

    public MatchResultsDTO(Integer team1Points, Integer team2Points) {
        this.team1Points = team1Points;
        this.team2Points = team2Points;
    }

    public Integer getTeam1Points() {
        return team1Points;
    }

    public Integer getTeam2Points() {
        return team2Points;
    }

    public void setTeam1Points(Integer team1Points) {
        this.team1Points = team1Points;
    }

    public void setTeam2Points(Integer team2Points) {
        this.team2Points = team2Points;
    }
}
