package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.MatchDTO;
import fmi.sports.tournament.organizer.backend.dtos.MatchResultsDTO;
import fmi.sports.tournament.organizer.backend.entities.tournament.match.Match;
import fmi.sports.tournament.organizer.backend.response.MatchResponse;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments/{tId}/matches")
public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<MatchResponse> schedule(@PathVariable("tId") Long tournamentId,
                                                  @RequestBody MatchDTO newMatch) {
        newMatch.setTournamentId(tournamentId);
        MatchDTO scheduledMatch = matchService.schedule(newMatch);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(MatchResponse
                        .fromDTO(scheduledMatch)
                        .responseResult(ResponseResult.SUCCESSFULLY_SCHEDULED)
                        .build());
    }

    @GetMapping
    public List<MatchDTO> getAllMatchedForTournament(@PathVariable("tId") Long tournamentId) {
        return matchService.getAllForTournament(tournamentId);
    }

    @GetMapping("/{mId}")
    public ResponseEntity<MatchResponse> getById(@PathVariable("mId") Long matchId) {
        MatchDTO matchDTO = matchService.getById(matchId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        MatchResponse.fromDTO(matchDTO)
                                .responseResult(ResponseResult.SUCCESSFULLY_FOUND)
                                .build()
                );
    }

    @PatchMapping("/{mId}/results")
    public ResponseEntity<MatchResponse> updateMatchScore(@PathVariable("mId") Long matchId,
                                           @RequestBody MatchResultsDTO newScore) {
        MatchDTO updatedMatch = matchService.updateResults(matchId, newScore);
        return ResponseEntity.ok()
                .body(
                        MatchResponse.fromDTO(updatedMatch)
                                .responseResult(ResponseResult.SUCCESSFULLY_UPDATED)
                                .build()
                );
    }

    @DeleteMapping("/{mId}")
    public ResponseEntity<MatchResponse> delete(@PathVariable("mId") Long matchId) {
        MatchDTO oldMatch = matchService.deleteById(matchId);
        return ResponseEntity.ok()
                .body(
                        MatchResponse.fromDTO(oldMatch)
                                .responseResult(ResponseResult.SUCCESSFULLY_DELETED)
                                .build()
                );
    }

    @PatchMapping("/{mId}/finished")
    public ResponseEntity<MatchResponse> markAsFinished(@PathVariable("mId") Long matchId) {
        MatchDTO updatedMatch = matchService.markAsFinished(matchId);
        return ResponseEntity.ok()
                .body(
                        MatchResponse.fromDTO(updatedMatch)
                                .responseResult(ResponseResult.SUCCESSFULLY_UPDATED)
                                .build()
                );
    }
}
