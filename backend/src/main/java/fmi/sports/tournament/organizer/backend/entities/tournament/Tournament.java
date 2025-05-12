package fmi.sports.tournament.organizer.backend.entities.tournament;

import fmi.sports.tournament.organizer.backend.entities.team.Team;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String sportType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double registrationFee;
    private Integer maxTeams;

    @ManyToMany
    @JoinTable(name = "tournament_team",
               joinColumns = @JoinColumn(name = "tournament_id"),
               inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams;

    public Tournament() {

    }

    public Tournament(String name,
                      String location,
                      String sportType,
                      LocalDate startDate,
                      LocalDate endDate,
                      Double registrationFee,
                      Integer maxTeams) {
        this.name = name;
        this.location = location;
        this.sportType = sportType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registrationFee = registrationFee;
        this.maxTeams = maxTeams;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getSportType() {
        return sportType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getRegistrationFee() {
        return registrationFee;
    }

    public Integer getMaxTeams() {
        return maxTeams;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRegistrationFee(Double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setMaxTeams(Integer maxTeams) {
        this.maxTeams = maxTeams;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
