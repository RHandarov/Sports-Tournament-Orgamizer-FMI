package fmi.sports.tournament.organizer.backend.exceptions.handler;

import fmi.sports.tournament.organizer.backend.exceptions.*;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
import fmi.sports.tournament.organizer.backend.response.TeamResponse;
import fmi.sports.tournament.organizer.backend.response.TournamentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoTournamentWithSuchIdException.class)
    public ResponseEntity<TournamentResponse> handleNoTournamentWithSuchIdException(NoTournamentWithSuchIdException ex) {
        return ResponseEntity.status(
                HttpStatus.NOT_FOUND)
                .body(TournamentResponse.builder().responseResult(ResponseResult.ID_NOT_FOUND).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(InappropriateMomentException.class)
    public ResponseEntity<TeamResponse> handleInappropriateMomentException(InappropriateMomentException ex) {
        return ResponseEntity.status(
                        HttpStatus.BAD_REQUEST)
                .body(TeamResponse.builder().responseResult(ResponseResult.TOURNAMENT_ALREADY_STARTED_OR_FINISHED).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(TeamAlreadyRegisteredException.class)
    public ResponseEntity<TeamResponse> handleTeamAlreadyRegisteredException(TeamAlreadyRegisteredException ex) {
        return ResponseEntity.status(
                        HttpStatus.BAD_REQUEST)
                .body(TeamResponse.builder().responseResult(ResponseResult.TEAM_ALREADY_REGISTERED).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(NoTeamWithSuchIdException.class)
    public ResponseEntity<TeamResponse> handleNoTeamWithSuchIdException(NoTeamWithSuchIdException ex) {
        return ResponseEntity.status(
                        HttpStatus.NOT_FOUND)
                .body(TeamResponse.builder().responseResult(ResponseResult.ID_NOT_FOUND).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(NoPlacesAvailableException.class)
    public ResponseEntity<TeamResponse> handleNoPlacesAvailableException(NoPlacesAvailableException ex) {
        return ResponseEntity.status(
                        HttpStatus.BAD_REQUEST)
                .body(TeamResponse.builder().responseResult(ResponseResult.NO_MORE_PLACES_AVAILABLE).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(NoSufficientMoneyException.class)
    public ResponseEntity<TeamResponse> handleNoSufficientMoneyException(NoSufficientMoneyException ex) {
        return ResponseEntity.status(
                        HttpStatus.BAD_REQUEST)
                .body(TeamResponse.builder().responseResult(ResponseResult.INSUFFICIENT_MONEY).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }

}
