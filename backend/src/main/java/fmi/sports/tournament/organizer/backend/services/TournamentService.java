package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TournamentService {
    TournamentDTO create(TournamentDTO newTournament);
    List<TournamentDTO> getAll();
    Optional<TournamentDTO> getById(Long tournamentId);
    void update(TournamentDTO updatedTournament);
    void deleteById(Long tournamentId);
    List<TeamDTO> getAllParticipatingTeams(Long tournamentId);
    void registerTeamForParticipation(Long tournamentId, Long teamId);
    void unregisterTeamForParticipation(Long tournamentId, Long teamId);
}
