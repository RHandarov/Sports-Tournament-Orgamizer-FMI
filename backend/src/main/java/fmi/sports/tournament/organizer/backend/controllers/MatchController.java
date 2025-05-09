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
    public ResponseEntity schedule(@PathVariable("tId") Long tournamentId,
                                   @RequestBody MatchDTO newMatch) {
        newMatch.setTournamentId(tournamentId);
        try {
            matchService.schedule(newMatch);
        } catch (IllegalArgumentException _) {
            return ResponseEntity.badRequest().build();
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
    public ResponseEntity updateMatchScore(@PathVariable("mId") Long matchId,
                                           @RequestBody MatchResultsDTO newScore) {
        if (matchService.updateResults(matchId, newScore)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{mId}")
    public ResponseEntity delete(@PathVariable("mId") Long matchId) {
        if (matchService.deleteById(matchId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{mId}/finished")
    public ResponseEntity markAsFinished(@PathVariable("mId") Long matchId) {
        if (matchService.markAsFinished(matchId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
