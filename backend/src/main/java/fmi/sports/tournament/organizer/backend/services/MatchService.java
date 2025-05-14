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
    MatchDTO getById(Long matchId);
    MatchDTO deleteById(Long matchId);
    MatchDTO updateResults(Long matchId, MatchResultsDTO newResults);
    MatchDTO markAsFinished(Long matchId);
}
