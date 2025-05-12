package fmi.sports.tournament.organizer.backend.entities.team;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private ParticipantCategory category;

    public Participant() {

    }

    public Participant(User user, Team team, ParticipantCategory category) {
        this.user = user;
        this.team = team;
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }

    public ParticipantCategory getCategory() {
        return category;
    }

    public void setCategory(ParticipantCategory category) {
        this.category = category;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
