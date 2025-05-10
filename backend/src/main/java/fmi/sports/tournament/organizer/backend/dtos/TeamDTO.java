package fmi.sports.tournament.organizer.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import fmi.sports.tournament.organizer.backend.entities.team.Team;

public class TeamDTO {
    public static TeamDTO fromEntity(Team team) {
        return new TeamDTO(team.getId(),
                team.getName(),
                team.getEmail(),
                team.getBudget(),
                team.getSize(),
                team.getSecretCode());
    }

    private Long id;

    @JsonProperty(value = "teamName")
    private String name;

    @JsonProperty(value = "contactEmail")
    private String email;
    private Double budget;

    @JsonProperty(value = "maxMembers")
    private Integer size;
    private String secretCode;

    public TeamDTO() {

    }

    public TeamDTO(Long id,
                   String name,
                   String email,
                   Double budget,
                   Integer size,
                   String secretCode) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
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
}
