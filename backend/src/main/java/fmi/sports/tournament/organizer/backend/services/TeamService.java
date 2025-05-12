package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    List<TeamDTO> getAll();
    Optional<TeamDTO> getById(Long teamId);
    TeamDTO create(TeamDTO newTeam);
    void update(TeamDTO updatedTeam);
    void deleteById(Long teamId);
}
