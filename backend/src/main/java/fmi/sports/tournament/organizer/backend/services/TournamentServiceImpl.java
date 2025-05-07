package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import fmi.sports.tournament.organizer.backend.dtos.mappers.TournamentDTOMapper;
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

        return TournamentDTOMapper.toDTO(
                tournamentsRepository.save(TournamentDTOMapper.toEntity(newTournament)));
    }

    @Override
    public List<TournamentDTO> getAll() {
        return tournamentsRepository.findAll()
                .stream()
                .map(TournamentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TournamentDTO> getById(Long tournamentId) {
        return tournamentsRepository.findById(tournamentId)
                .map(TournamentDTOMapper::toDTO);
    }

    @Override
    public boolean update(TournamentDTO updatedTournament) {
        if (getById(updatedTournament.getId()).isEmpty()) {
            return false;
        }

        tournamentsRepository.save(TournamentDTOMapper.toEntity(updatedTournament));
        return true;
    }

    @Override
    public boolean deleteById(Long tournamentId) {
        tournamentsRepository.deleteById(tournamentId);
        return true;
    }
}
