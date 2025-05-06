package fmi.sports.tournament.organizer.backend.entities.user;

import fmi.sports.tournament.organizer.backend.entities.team.Participant;
import fmi.sports.tournament.organizer.backend.entities.team.Team;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password; // will be hashed
    private LocalDate birthDate;
    private LocalDate creationDate;
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private Participant participant;

    @ManyToMany
    @JoinTable(name = "followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> followedTeams;

    public User(String firstName,
                String lastName,
                String email,
                String password,
                LocalDate birthDate,
                UserRole role,
                Participant participant,
                Set<Team> followedTeams) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.creationDate = LocalDate.now();
        this.isActive = false;
        this.role = role;
        this.participant = participant;
        this.followedTeams = followedTeams;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserRole getRole() {
        return role;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Set<Team> getFollowedTeams() {
        return followedTeams;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public void setFollowedTeams(Set<Team> followedTeams) {
        this.followedTeams = followedTeams;
    }
}
