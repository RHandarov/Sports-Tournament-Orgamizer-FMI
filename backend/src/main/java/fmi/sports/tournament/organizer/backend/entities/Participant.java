package fmi.sports.tournament.organizer.backend.entities;

import jakarta.persistence.*;

@Entity
public class Participant {
    @Id
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private ParticipantCategory category;

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
}
