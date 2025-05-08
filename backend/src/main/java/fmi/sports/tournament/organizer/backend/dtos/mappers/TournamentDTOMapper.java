package fmi.sports.tournament.organizer.backend.dtos.mappers;

import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;

public class TournamentDTOMapper {
    public static TournamentDTO toDTO(Tournament tournament) {
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

    public static Tournament toEntity(TournamentDTO tournamentDTO) {
        Tournament entity = new Tournament(tournamentDTO.getName(),
                tournamentDTO.getLocation(),
                tournamentDTO.getSportType(),
                tournamentDTO.getStartDate(),
                tournamentDTO.getEndDate(),
                tournamentDTO.getRegistrationFee(),
                tournamentDTO.getMaxTeams());

        if (!TournamentDTO.ID_NOT_SET.equals(tournamentDTO.getId())) {
            entity.setId(tournamentDTO.getId());
        }

        return entity;
    }
}
