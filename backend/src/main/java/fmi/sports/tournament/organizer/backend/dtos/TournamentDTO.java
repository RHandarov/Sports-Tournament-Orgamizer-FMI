package fmi.sports.tournament.organizer.backend.dtos;

import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;

import java.time.LocalDate;

public class TournamentDTO {
    public static final Long ID_NOT_SET = -1L;

    public static TournamentDTO fromEntity(Tournament tournament) {
        TournamentDTO dto = new TournamentDTO(tournament.getName(),
                tournament.getLocation(),
                tournament.getSportType(),
                tournament.getStartDate(),
                tournament.getEndDate(),
                tournament.getRegistrationFee(),
                tournament.getMaxTeams());
        dto.setId(tournament.getId());

        return dto;
    }

    private Long id;
    private String name;
    private String location;
    private String sportType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double registrationFee;
    private Integer maxTeams;

    public TournamentDTO(String name,
                         String location,
                         String sportType,
                         LocalDate startDate,
                         LocalDate endDate,
                         Double registrationFee,
                         Integer maxTeams) {
        this.id = ID_NOT_SET;
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
}
