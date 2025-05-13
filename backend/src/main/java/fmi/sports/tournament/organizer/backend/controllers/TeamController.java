package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.response.TeamResponse;
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
    public ResponseEntity<TeamResponse> getById(@PathVariable("id") Long teamId) {
        TeamDTO dto = teamService.getById(teamId);
        TeamResponse response = TeamResponse
                .fromDTO(dto)
                .responseResult(ResponseResult.SUCCESSFULLY_FOUND)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TeamResponse> create(@RequestBody TeamDTO newTeam) {
        TeamDTO teamDTO = teamService.create(newTeam);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        TeamResponse.fromDTO(teamDTO).responseResult(ResponseResult.SUCCESSFULLY_CREATED)
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<TeamResponse> update(@RequestBody TeamDTO updatedTeams) {

        TeamDTO updated = teamService.update(updatedTeams);

        return ResponseEntity.ok(
                TeamResponse
                        .fromDTO(updated)
                        .responseResult(ResponseResult.SUCCESSFULLY_UPDATED)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamResponse> deleteById(@PathVariable("id") Long teamId) {
        TeamDTO teamDTO = teamService.deleteById(teamId);
        return ResponseEntity.ok(
                TeamResponse
                        .fromDTO(teamDTO)
                        .responseResult(ResponseResult.SUCCESSFULLY_DELETED)
                        .build()
        );
    }
}
