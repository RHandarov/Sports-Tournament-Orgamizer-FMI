package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.response.StandingResponse;
import fmi.sports.tournament.organizer.backend.response.TeamResponse;
import fmi.sports.tournament.organizer.backend.response.TournamentResponse;
import fmi.sports.tournament.organizer.backend.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tournaments")
public class TournamentController {
    private final TournamentService tournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<TournamentDTO> getAll() {
        return tournamentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentResponse> getById(@PathVariable("id") Long tournamentId) {

        TournamentDTO tournamentDTO = tournamentService.getById(tournamentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TournamentResponse
                        .fromDTO(tournamentDTO)
                        .responseResult(ResponseResult.SUCCESSFULLY_FOUND)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<TournamentResponse> create(@Valid @RequestBody TournamentDTO newTournament) {
        TournamentDTO tournamentDTO = tournamentService.create(newTournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TournamentResponse
                        .fromDTO(tournamentDTO)
                        .responseResult(ResponseResult.SUCCESSFULLY_CREATED)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentResponse> update(@Valid @RequestBody TournamentDTO updatedTournament, @PathVariable long id) {
        tournamentService.updateById(updatedTournament, id);

        return ResponseEntity.ok(
                TournamentResponse
                        .fromDTO(updatedTournament)
                        .id(id)
                        .responseResult(ResponseResult.SUCCESSFULLY_UPDATED)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TournamentResponse> delete(@PathVariable("id") Long tournamentId) {
        TournamentDTO tournamentDTO = tournamentService.deleteById(tournamentId);
        return ResponseEntity.ok(
                TournamentResponse.fromDTO(tournamentDTO)
                        .responseResult(ResponseResult.SUCCESSFULLY_DELETED)
                        .build()
        );
    }

    @GetMapping("/{id}/teams")
    public ResponseEntity<List<TeamDTO>> getAllParticipatingTeams(@PathVariable("id") Long tournamentId) {
        List<TeamDTO> teams = tournamentService.getAllParticipatingTeams(tournamentId);
        return ResponseEntity.ok(teams);
    }

    @PostMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<TeamResponse> registerTeamForParticipation(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("teamId") Long teamId) {

        tournamentService.registerTeamForParticipation(tournamentId, teamId);

        return ResponseEntity.ok(
                TeamResponse.builder()
                        .responseResult(ResponseResult.SUCCESSFULLY_REGISTERED)
                        .message(String.format("Team with id %d successfully registered for tournament with id %d.", teamId, tournamentId))
                        .build()
        );
    }

    @DeleteMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<TeamResponse> unregisterTeamForParticipation(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("teamId") Long teamId) {

        tournamentService.unregisterTeamForParticipation(tournamentId, teamId);

        return ResponseEntity.ok(
                TeamResponse.builder()
                        .responseResult(ResponseResult.SUCCESSFULLY_UNREGISTERED)
                        .message(String.format("Team with id %d successfully unregistered for tournament with id %d.", teamId, tournamentId))
                        .build()
        );
    }

    @GetMapping("/{tournamentId}/standings")
    public List<StandingResponse> getTournamentStandings(@PathVariable("tournamentId") Long tournamentId) {
        return tournamentService
                .getTournamentStandings(tournamentId)
                .stream()
                .map(standingDTO -> StandingResponse.fromDTO(standingDTO).build())
                .collect(Collectors.toList());
    }
}
