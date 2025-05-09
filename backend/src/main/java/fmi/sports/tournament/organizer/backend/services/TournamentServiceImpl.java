package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;
import fmi.sports.tournament.organizer.backend.repositories.TournamentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {
    private final TournamentsRepository tournamentsRepository;

    @Autowired
    public TournamentServiceImpl(TournamentsRepository tournamentsRepository) {
        this.tournamentsRepository = tournamentsRepository;
    }

    @Override
    public TournamentDTO create(TournamentDTO newTournament) {
        if (getById(newTournament.getId()).isPresent()) {
            return newTournament;
        }

        Tournament entity = new Tournament(newTournament.getName(),
                newTournament.getLocation(),
                newTournament.getSportType(),
                newTournament.getStartDate(),
                newTournament.getEndDate(),
                newTournament.getRegistrationFee(),
                newTournament.getMaxTeams());

        return TournamentDTO.fromEntity(tournamentsRepository.save(entity));
    }

    @Override
    public List<TournamentDTO> getAll() {
        return tournamentsRepository.findAll()
                .stream()
                .map(TournamentDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TournamentDTO> getById(Long tournamentId) {
        return tournamentsRepository.findById(tournamentId)
                .map(TournamentDTO::fromEntity);
    }

    @Override
    public boolean update(TournamentDTO updatedTournament) {
        Optional<Tournament> tournamentOptional =
                tournamentsRepository.findById(updatedTournament.getId());
        if (tournamentOptional.isEmpty()) {
            return false;
        }

        Tournament tournament = tournamentOptional.get();
        tournament.setLocation(updatedTournament.getLocation());
        tournament.setName(updatedTournament.getName());
        tournament.setEndDate(updatedTournament.getEndDate());
        tournament.setMaxTeams(updatedTournament.getMaxTeams());
        tournament.setRegistrationFee(updatedTournament.getRegistrationFee());
        tournament.setSportType(updatedTournament.getSportType());
        tournament.setStartDate(updatedTournament.getStartDate());

        tournamentsRepository.save(tournament);
        return true;
    }

    @Override
    public boolean deleteById(Long tournamentId) {
        tournamentsRepository.deleteById(tournamentId);
        return true;
    }
}
