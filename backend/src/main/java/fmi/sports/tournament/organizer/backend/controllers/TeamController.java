package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.ParticipantRegisterDTO;
import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.entities.user.User;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.response.TeamResponse;
import fmi.sports.tournament.organizer.backend.services.JWTService;
import fmi.sports.tournament.organizer.backend.services.TeamService;
import jakarta.validation.Valid;
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
    private final JWTService jwtService;

    @Autowired
    public TeamController(TeamService teamService, JWTService jwtService) {
        this.teamService = teamService;
        this.jwtService = jwtService;
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
    public ResponseEntity<TeamResponse> create(@Valid @RequestBody TeamDTO newTeam) {
        TeamDTO teamDTO = teamService.create(newTeam);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        TeamResponse.fromDTO(teamDTO).responseResult(ResponseResult.SUCCESSFULLY_CREATED)
                                .build()
                );
    }

    @PutMapping
    public ResponseEntity<TeamResponse> update(@Valid @RequestBody TeamDTO updatedTeams) {

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

    @PostMapping("/{teamId}/participants")
    public ResponseEntity<String> registerUserForTeam(@RequestHeader("Authorization") String authorizationHeader,
                                                      @RequestBody ParticipantRegisterDTO participantRegisterDTO, @PathVariable("teamId") Long teamId) {

        String token = authorizationHeader.startsWith("Bearer ") ?
                authorizationHeader.substring(7) : authorizationHeader;

        User user = jwtService.getUser(token);
        Long userId = user.getId();
        teamService.registerUserForTeam(userId,teamId,participantRegisterDTO);

        return ResponseEntity.ok("User successfully registered for team with id " + teamId);
    }
}
