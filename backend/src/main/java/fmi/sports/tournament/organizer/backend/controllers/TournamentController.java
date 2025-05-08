package fmi.sports.tournament.organizer.backend.controllers;

import fmi.sports.tournament.organizer.backend.dtos.TournamentDTO;
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
    public ResponseEntity<TournamentDTO> getById(@PathVariable("id") Long tournamentId) {
        Optional<TournamentDTO> dto =
                tournamentService.getById(tournamentId);

        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.of(dto);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TournamentDTO newTournament) {
        tournamentService.create(newTournament);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TournamentDTO updatedTournament) {
        if (tournamentService.update(updatedTournament)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long tournamentId) {
        if (tournamentService.deleteById(tournamentId)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
