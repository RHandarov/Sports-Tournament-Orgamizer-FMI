package fmi.sports.tournament.organizer.backend.entities.tournament.match;

import fmi.sports.tournament.organizer.backend.entities.team.Team;
import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;
import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private Tournament tournament;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_1")
    private Team team1;
    private Integer team1Points;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_2")
    private Team team2;
    private Integer team2Points;

    private String venue;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    public Match(Tournament tournament,
                 Team team1,
                 Team team2,
                 String venue) {
        this.tournament = tournament;
        this.team1 = team1;
        this.team1Points = 0;
        this.team2 = team2;
        this.team2Points = 0;
        this.venue = venue;
        this.status = MatchStatus.ONGOING;
    }

    public Long getId() {
        return id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Team getTeam1() {
        return team1;
    }

    public Integer getTeam1Points() {
        return team1Points;
    }

    public Team getTeam2() {
        return team2;
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
}
