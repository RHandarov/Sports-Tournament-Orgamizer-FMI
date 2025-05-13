package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.entities.auth.TokenGenerator;
import fmi.sports.tournament.organizer.backend.entities.team.Team;
import fmi.sports.tournament.organizer.backend.entities.tournament.Tournament;
import fmi.sports.tournament.organizer.backend.exceptions.NoTeamWithSuchIdException;
import fmi.sports.tournament.organizer.backend.repositories.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private static final int SECRET_CODE_LENGTH = 9;

    private final TeamsRepository teamsRepository;

    @Autowired
    public TeamServiceImpl(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    @Override
    public TeamDTO create(TeamDTO newTeam) {

        Team newEntity = new Team(newTeam.getName(),
                newTeam.getEmail(),
                newTeam.getBudget(),
                newTeam.getSize(),
                TokenGenerator.generateRandomToken(SECRET_CODE_LENGTH));
        return TeamDTO.fromEntity(teamsRepository.save(newEntity));
    }

    @Override
    public TeamDTO getById(Long teamId) {
        TeamDTO teamDTO = teamsRepository.findById(teamId)
                .map(TeamDTO::fromEntity).orElseThrow(
                        () -> new NoTeamWithSuchIdException(String.format("Team with id %d does not exist", teamId))
                );
        return teamDTO;
    }

    @Override
    public List<TeamDTO> getAll() {
        return teamsRepository.findAll()
                .stream()
                .map(TeamDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO update(TeamDTO updatedTeam) {
        Team team = getTeamEntityById(updatedTeam.getId());
        team.setName(updatedTeam.getName());
        team.setEmail(updatedTeam.getEmail());
        team.setBudget(updatedTeam.getBudget());
        team.setSize(updatedTeam.getSize());
        if (updatedTeam.getSecretCode() != null) {
            team.setSecretCode(updatedTeam.getSecretCode());
        }
        Team saved = teamsRepository.save(team);
        return TeamDTO.fromEntity(saved);
    }

    private Team getTeamEntityById(Long teamId) {
        return teamsRepository.findById(teamId).orElseThrow(
                () -> new NoTeamWithSuchIdException(String.format("Team with id %d does not exist", teamId))
        );
    }

    @Override
    public TeamDTO deleteById(Long teamId) {
        Team team = getTeamEntityById(teamId);

        for (Tournament tournament : team.getTournaments()) {
            tournament.getTeams().remove(team);
        }

        team.getTournaments().clear();

        teamsRepository.deleteById(teamId);
        return TeamDTO.fromEntity(team);
    }
}
