package fmi.sports.tournament.organizer.backend.dtos;

import fmi.sports.tournament.organizer.backend.entities.tournament.match.Match;
import fmi.sports.tournament.organizer.backend.entities.tournament.match.MatchStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MatchDTO {
    public static final Long ID_NOT_SET = -1L;

    public static MatchDTO fromEntity(Match match) {
        MatchDTO dto = new MatchDTO(match.getTournament().getId(),
                match.getTeam1().getId(),
                match.getTeam1Points(),
                match.getTeam2().getId(),
                match.getTeam2Points(),
                match.getVenue(),
                match.getStatus());
        dto.setId(match.getId());

        return dto;
    }

    private Long id;

//    @NotNull(message = "Tournament ID is required")
    @Positive(message = "Tournament ID must be a positive number")
    private Long tournamentId;

    @NotNull(message = "Team 1 ID is required")
    @Positive(message = "Team 1 ID must be a positive number")
    private Long team1Id;

    @NotNull(message = "Team 1 points are required")
    @Min(value = 0, message = "Team 1 points must be zero or a positive number")
    private Integer team1Points;

    @NotNull(message = "Team 2 ID is required")
    @Positive(message = "Team 2 ID must be a positive number")
    private Long team2Id;

    @NotNull(message = "Team 2 points are required")
    @Min(value = 0, message = "Team 2 points must be zero or a positive number")
    private Integer team2Points;

    @NotNull(message = "Venue is required")
    private String venue;

    @NotNull(message = "Match status is required")
    private MatchStatus status;

    public MatchDTO() {
        this.id = ID_NOT_SET;
        this.team1Points = 0;
        this.team2Points = 0;
        this.status = MatchStatus.ONGOING;
    }

    public MatchDTO(Long tournamentId,
                    Long team1Id,
                    Integer team1Points,
                    Long team2Id,
                    Integer team2Points,
                    String venue,
                    MatchStatus status) {
        this.id = ID_NOT_SET;
        this.tournamentId = tournamentId;
        this.team1Id = team1Id;
        this.team1Points = team1Points;
        this.team2Id = team2Id;
        this.team2Points = team2Points;
        this.venue = venue;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public Long getTeam1Id() {
        return team1Id;
    }

    public Integer getTeam1Points() {
        return team1Points;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public Integer getTeam2Points() {
        return team2Points;
    }

    public String getVenue() {
        return venue;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setTeam1Points(Integer team1Points) {
        this.team1Points = team1Points;
    }

    public void setTeam2Points(Integer team2Points) {
        this.team2Points = team2Points;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
    }
}
