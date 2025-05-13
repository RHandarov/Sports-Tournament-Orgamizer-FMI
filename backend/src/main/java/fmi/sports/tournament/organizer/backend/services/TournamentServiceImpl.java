package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import fmi.sports.tournament.organizer.backend.entities.team.Team;
import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;
import fmi.sports.tournament.organizer.backend.exceptions.InappropriateMomentException;
import fmi.sports.tournament.organizer.backend.exceptions.NoPlacesAvailableException;
import fmi.sports.tournament.organizer.backend.exceptions.NoSufficientMoneyException;
import fmi.sports.tournament.organizer.backend.exceptions.NoTournamentWithSuchIdException;
import fmi.sports.tournament.organizer.backend.repositories.TeamsRepository;
import fmi.sports.tournament.organizer.backend.repositories.TournamentsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TournamentServiceImpl implements TournamentService {
    private final TournamentsRepository tournamentsRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public TournamentServiceImpl(TournamentsRepository tournamentsRepository,
                                 TeamsRepository teamsRepository) {
        this.tournamentsRepository = tournamentsRepository;
        this.teamsRepository = teamsRepository;
    }

    @Override
    public TournamentDTO create(TournamentDTO newTournament) {

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
    public TournamentDTO getById(Long tournamentId) {
        Tournament tournament = tournamentsRepository.findById(tournamentId).orElseThrow(
                () -> new NoTournamentWithSuchIdException(String.format("Tournament with id %d does not exist", tournamentId))
        );
        return TournamentDTO.fromEntity(tournament);
    }

    @Override
    public void updateById(TournamentDTO updatedTournament, long id) {
          tournamentsRepository
                .findById(id)
                .orElseThrow(() -> new NoTournamentWithSuchIdException(String.format("Tournament with id %d does not exist", id)));

        Tournament tournament = Tournament.fromDTO(updatedTournament);
        tournament.setId(id);
        tournamentsRepository.save(tournament);
    }

    @Override
    public TournamentDTO deleteById(Long tournamentId) {
        Tournament tournament = tournamentsRepository.findById(tournamentId).orElseThrow(
                () -> new NoTournamentWithSuchIdException(String.format("Tournament with id %d does not exist", tournamentId))
        );
        TournamentDTO tournamentDTO = TournamentDTO.fromEntity(tournament);
        tournamentsRepository.deleteById(tournamentId);
        return tournamentDTO;
    }

    @Override
    public List<TeamDTO> getAllParticipatingTeams(Long tournamentId) {
        return getTournamentEntityById(tournamentId)
                .getTeams()
                .stream()
                .map(TeamDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void registerTeamForParticipation(Long tournamentId, Long teamId) {
        Tournament tournament = getTournamentEntityById(tournamentId);
        if (LocalDate.now().isAfter(tournament.getStartDate())
                || LocalDate.now().equals(tournament.getStartDate())) {
            throw new InappropriateMomentException("Team cannot register after start of tournament!");
        }

        Team team = getTeamEntityById(teamId);

        if (tournament.getTeams().contains(team)) {
            return;
        }

        if (team.getBudget() < tournament.getRegistrationFee()) {
            throw new NoSufficientMoneyException("Not enough money for registration for this tournament!");
        }

        if (tournament.getTeams().size() == tournament.getMaxTeams()) {
            throw new NoPlacesAvailableException("There are not more places for registration in this tournament!");
        }

        tournament.getTeams().add(team);
        team.setBudget(team.getBudget() - tournament.getRegistrationFee());
        tournamentsRepository.save(tournament);
        teamsRepository.save(team);
    }

    @Override
    public void unregisterTeamForParticipation(Long tournamentId, Long teamId) {
        Tournament tournament = getTournamentEntityById(tournamentId);
        if (LocalDate.now().isAfter(tournament.getStartDate())
                || LocalDate.now().equals(tournament.getStartDate())) {
            throw new InappropriateMomentException("Team cannot unregister after start of tournament!");
        }

        Team team = getTeamEntityById(teamId);

        if (!tournament.getTeams().contains(team)) {
            return;
        }

        tournament.getTeams().remove(team);
        team.setBudget(team.getBudget() + tournament.getRegistrationFee());
        tournamentsRepository.save(tournament);
        teamsRepository.save(team);
    }

    private Tournament getTournamentEntityById(Long tournamentId) {
        Optional<Tournament> tournamentOptional =
                tournamentsRepository.findById(tournamentId);

        if (tournamentOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "Tournament with id " + tournamentId + " doesn't exists!"
            );
        }

        return tournamentOptional.get();
    }

    private Team getTeamEntityById(Long teamId) {
        Optional<Team> teamOptional =
                teamsRepository.findById(teamId);

        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException(
                    "Team with id " + teamId + " doesn't exists!"
            );
        }

        return teamOptional.get();
    }
}
