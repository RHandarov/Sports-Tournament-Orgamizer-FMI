package fmi.sports.tournament.organizer.backend.entities.tournament;

import fmi.sports.tournament.organizer.backend.entities.team.Team;
import jakarta.persistence.*;

@Entity
@Table(name = "standings")
public class Standing {
    @Id
    @OneToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Id
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    private Integer points;

    public Standing(Tournament tournament, Team team) {
        this.tournament = tournament;
        this.team = team;
        this.points = 0;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Team getTeam() {
        return team;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
