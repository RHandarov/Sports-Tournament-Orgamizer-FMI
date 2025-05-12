package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.MatchDTO;
import fmi.sports.tournament.organizer.backend.dtos.MatchResultsDTO;
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
    public ResponseEntity<String> schedule(@PathVariable("tId") Long tournamentId,
                                   @RequestBody MatchDTO newMatch) {
        newMatch.setTournamentId(tournamentId);
        try {
            matchService.schedule(newMatch);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<MatchDTO> getAllMatchedForTournament(@PathVariable("tId") Long tournamentId) {
        return matchService.getAllForTournament(tournamentId);
    }

    @GetMapping("/{mId}")
    public ResponseEntity<MatchDTO> getById(@PathVariable("mId") Long matchId) {
        Optional<MatchDTO> dto = matchService.getById(matchId);
        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(dto);
    }

    @PatchMapping("/{mId}/results")
    public ResponseEntity<String> updateMatchScore(@PathVariable("mId") Long matchId,
                                           @RequestBody MatchResultsDTO newScore) {
        try {
            matchService.updateResults(matchId, newScore);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{mId}")
    public ResponseEntity delete(@PathVariable("mId") Long matchId) {
        matchService.deleteById(matchId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{mId}/finished")
    public ResponseEntity<String> markAsFinished(@PathVariable("mId") Long matchId) {
        try {
            matchService.markAsFinished(matchId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().build();
    }
}
