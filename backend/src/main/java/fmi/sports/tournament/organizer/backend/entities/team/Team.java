package fmi.sports.tournament.organizer.backend.entities.team;

import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;
import fmi.sports.tournament.organizer.backend.entities.user.User;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Double budget;
    private Integer size;
    private String secretCode;
    //TODO: add other columns in DB model

    @OneToMany(mappedBy = "team")
    private Set<Participant> participants;

    @ManyToMany(mappedBy = "followedTeams")
    private Set<User> followers;

    @ManyToMany(mappedBy = "teams")
    private Set<Tournament> tournaments;

    public Team() {

    }

    public Team(String name,
                String email,
                Double budget,
                Integer size,
                String secretCode) {
        this.name = name;
        this.email = email;
        this.budget = budget;
        this.size = size;
        this.secretCode = secretCode;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Double getBudget() {
        return budget;
    }

    public Integer getSize() {
        return size;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
}
