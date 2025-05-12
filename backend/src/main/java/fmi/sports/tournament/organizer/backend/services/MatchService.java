package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.MatchDTO;
import fmi.sports.tournament.organizer.backend.dtos.MatchResultsDTO;
import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MatchService {
    MatchDTO schedule(MatchDTO newMatch);
    List<MatchDTO> getAllForTournament(Long tournamentId);
    Optional<MatchDTO> getById(Long matchId);
    void deleteById(Long matchId);
    void updateResults(Long matchId, MatchResultsDTO newResults);
    void markAsFinished(Long matchId);
}
