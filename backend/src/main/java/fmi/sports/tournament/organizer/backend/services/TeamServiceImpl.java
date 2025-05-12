package fmi.sports.tournament.organizer.backend.services;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.entities.auth.TokenGenerator;
import fmi.sports.tournament.organizer.backend.entities.team.Team;
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
        if (getById(newTeam.getId()).isPresent()) {
            return newTeam;
        }

        Team newEntity = new Team(newTeam.getName(),
                newTeam.getEmail(),
                newTeam.getBudget(),
                newTeam.getSize(),
                TokenGenerator.generateRandomToken(SECRET_CODE_LENGTH));
        return TeamDTO.fromEntity(teamsRepository.save(newEntity));
    }

    @Override
    public Optional<TeamDTO> getById(Long teamId) {
        return teamsRepository.findById(teamId)
                .map(TeamDTO::fromEntity);
    }

    @Override
    public List<TeamDTO> getAll() {
        return teamsRepository.findAll()
                .stream()
                .map(TeamDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void update(TeamDTO updatedTeam) {
        Team team = getTeamEntityById(updatedTeam.getId());
        team.setName(updatedTeam.getName());
        team.setEmail(updatedTeam.getEmail());
        team.setBudget(updatedTeam.getBudget());
        team.setSize(updatedTeam.getSize());
        if (updatedTeam.getSecretCode() != null) {
            team.setSecretCode(updatedTeam.getSecretCode());
        }

        teamsRepository.save(team);
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
    public void deleteById(Long teamId) {
        teamsRepository.deleteById(teamId);
    }
}
