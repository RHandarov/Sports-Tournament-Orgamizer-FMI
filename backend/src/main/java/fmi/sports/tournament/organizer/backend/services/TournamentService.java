package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TournamentService {
    TournamentDTO create(TournamentDTO newTournament);
    List<TournamentDTO> getAll();
    Optional<TournamentDTO> getById(Long tournamentId);
    boolean update(TournamentDTO updatedTournament);
    boolean deleteById(Long tournamentId);
}
