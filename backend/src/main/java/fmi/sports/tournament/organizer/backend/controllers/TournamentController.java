package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.TeamDTO;
import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.response.TournamentResponse;
import fmi.sports.tournament.organizer.backend.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<TournamentResponse> create(@RequestBody TournamentDTO newTournament) {
        TournamentDTO tournamentDTO = tournamentService.create(newTournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TournamentResponse
                        .fromDTO(tournamentDTO)
                        .responseResult(ResponseResult.SUCCESSFULLY_CREATED)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentResponse> update(@RequestBody TournamentDTO updatedTournament, @PathVariable long id) {
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
        List<TeamDTO> teams;
        try {
            teams = tournamentService.getAllParticipatingTeams(tournamentId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(teams);
    }

    @PostMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<String> registerTeamForParticipation(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("teamId") Long teamId) {
        try {
            tournamentService.registerTeamForParticipation(tournamentId, teamId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{tournamentId}/teams/{teamId}")
    public ResponseEntity<String> unregisterTeamForParticipation(
            @PathVariable("tournamentId") Long tournamentId,
            @PathVariable("teamId") Long teamId) {
        try {
            tournamentService.unregisterTeamForParticipation(tournamentId, teamId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
