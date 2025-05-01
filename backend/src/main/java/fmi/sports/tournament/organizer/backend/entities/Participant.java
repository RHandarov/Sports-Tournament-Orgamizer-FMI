package fmi.sports.tournament.organizer.backend.entities;

public class Participant {
    private User user;
    private Team team;
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
