package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.ParticipantRegisterDTO;
import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    List<TeamDTO> getAll();
    TeamDTO getById(Long teamId);
    TeamDTO create(TeamDTO newTeam);
    TeamDTO update(TeamDTO updatedTeam);
    TeamDTO deleteById(Long teamId);

    void registerUserForTeam(Long userId, Long teamId, ParticipantRegisterDTO participantRegisterDTO);
}
