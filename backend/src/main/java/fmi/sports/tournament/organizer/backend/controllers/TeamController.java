package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDTO> getAll() {
        return teamService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable("id") Long teamId) {
        Optional<TeamDTO> dto =
                teamService.getById(teamId);

        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(dto);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TeamDTO newTeam) {
        teamService.create(newTeam);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody TeamDTO updatedTeams) {
        try {
            teamService.update(updatedTeams);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long teamId) {
        teamService.deleteById(teamId);
        return ResponseEntity.ok().build();
    }
}
