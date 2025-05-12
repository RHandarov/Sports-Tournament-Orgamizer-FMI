package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.MatchDTO;
import fmi.sports.tournament.organizer.backend.dtos.MatchResultsDTO;
import fmi.sports.tournament.organizer.backend.entities.team.Team;
import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;
import fmi.sports.tournament.organizer.backend.entities.tournament.match.Match;
import fmi.sports.tournament.organizer.backend.entities.tournament.match.MatchStatus;
import fmi.sports.tournament.organizer.backend.exceptions.CompletedMatchException;
import fmi.sports.tournament.organizer.backend.exceptions.TeamNotParticipatingException;
import fmi.sports.tournament.organizer.backend.repositories.MatchesRepository;
import fmi.sports.tournament.organizer.backend.repositories.TeamsRepository;
import fmi.sports.tournament.organizer.backend.repositories.TournamentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchesRepository matchesRepository;
    private final TournamentsRepository tournamentsRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public MatchServiceImpl(MatchesRepository matchesRepository,
                            TournamentsRepository tournamentsRepository,
                            TeamsRepository teamsRepository) {
        this.matchesRepository = matchesRepository;
        this.tournamentsRepository = tournamentsRepository;
        this.teamsRepository = teamsRepository;
    }

    @Override
    public MatchDTO schedule(MatchDTO newMatch) {
        Tournament tournament = getTournamentEntityById(newMatch.getTournamentId());
        Team team1 = getTeamEntityById(newMatch.getTeam1Id());
        if (!tournament.getTeams().contains(team1)) {
            throw new TeamNotParticipatingException(newMatch.getTeam1Id());
        }

        Team team2 = getTeamEntityById(newMatch.getTeam2Id());
        if (!tournament.getTeams().contains(team2)) {
            throw new TeamNotParticipatingException(newMatch.getTeam2Id());
        }

        Match match = new Match(tournament, team1, team2, newMatch.getVenue());
        return MatchDTO.fromEntity(matchesRepository.save(match));
    }

    private Team getTeamEntityById(Long teamId) {
        Optional<Team> teamOptional =
                teamsRepository.findById(teamId);

        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException("Team with id " + teamId + " doesn't exists!");
        }

        return teamOptional.get();
    }

    @Override
    public List<MatchDTO> getAllForTournament(Long tournamentId) {
        Tournament tournament = getTournamentEntityById(tournamentId);
        return matchesRepository.findByTournament(tournament)
                .stream()
                .map(MatchDTO::fromEntity)
                .collect(Collectors.toList());
    }

    private Tournament getTournamentEntityById(Long tournamentId) {
        Optional<Tournament> tournamentOptional =
                tournamentsRepository.findById(tournamentId);

        if (tournamentOptional.isEmpty()) {
            throw new IllegalArgumentException("Tournament with id " + tournamentId + "doesn't exists!");
        }

        return tournamentOptional.get();
    }

    @Override
    public Optional<MatchDTO> getById(Long matchId) {
        return matchesRepository.findById(matchId)
                .map(MatchDTO::fromEntity);
    }

    @Override
    public void deleteById(Long matchId) {
        matchesRepository.deleteById(matchId);
    }

    @Override
    public void updateResults(Long matchId, MatchResultsDTO newResults) {
        Match match = getMatchEntityById(matchId);
        if (match.getStatus() == MatchStatus.COMPLETED) {
            throw new CompletedMatchException(matchId);
        }

        match.setTeam1Points(newResults.getTeam1Points());
        match.setTeam2Points(newResults.getTeam2Points());
        matchesRepository.save(match);
    }

    @Override
    public void markAsFinished(Long matchId) {
        Match match = getMatchEntityById(matchId);
        match.setStatus(MatchStatus.COMPLETED);
        matchesRepository.save(match);
    }

    private Match getMatchEntityById(Long matchId) {
        Optional<Match> matchOptional = matchesRepository.findById(matchId);

        if (matchOptional.isEmpty()) {
            throw new IllegalArgumentException("Match with id " + matchId + " doesn't exists!");
        }

        return matchOptional.get();
    }
}
