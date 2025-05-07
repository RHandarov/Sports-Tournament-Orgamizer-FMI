package fmi.sports.tournament.organizer.backend.dtos;

import java.time.LocalDate;

public class TournamentDTO {
    private static final Long ID_NOT_SET = -1L;

    private Long id;
    private String name;
    private String location;
    private final String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double registrationFee;
    private Integer maxTeams;

    public TournamentDTO(String name,
                         String location,
                         String type,
                         LocalDate startDate,
                         LocalDate endDate,
                         Double registrationFee,
                         Integer maxTeams) {
        this.id = ID_NOT_SET;
        this.name = name;
        this.location = location;
        this.type = type;
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

    public String getType() {
        return type;
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
}
