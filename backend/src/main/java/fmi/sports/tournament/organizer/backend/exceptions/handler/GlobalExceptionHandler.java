package fmi.sports.tournament.organizer.backend.exceptions.handler;

import fmi.sports.tournament.organizer.backend.exceptions.NoTournamentWithSuchIdException;
import fmi.sports.tournament.organizer.backend.response.ResponseResult;
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



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }

}
