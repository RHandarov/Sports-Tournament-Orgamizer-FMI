package fmi.sports.tournament.organizer.backend.exceptions.handler;

import fmi.sports.tournament.organizer.backend.entities.user.User;
import fmi.sports.tournament.organizer.backend.exceptions.*;
import fmi.sports.tournament.organizer.backend.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(NoMatchWithSuchIdException.class)
    public ResponseEntity<MatchResponse> handleNoMatchWithSuchIdException(NoMatchWithSuchIdException ex) {
        return ResponseEntity.status(
                        HttpStatus.NOT_FOUND)
                .body(MatchResponse.builder().responseResult(ResponseResult.ID_NOT_FOUND).message(ex.getMessage()).build()
                );
    }

    @ExceptionHandler(TeamNotParticipatingException.class)
    public ResponseEntity<MatchResponse> handleTeamNotParticipatingException(TeamNotParticipatingException ex) {
        return ResponseEntity.badRequest()
                .body(
                        MatchResponse.builder()
                                .responseResult(ResponseResult.TEAM_NOT_PARTICIPATING_IN_TOURNAMENT)
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(CompletedMatchException.class)
    public ResponseEntity<MatchResponse> handleCompletedMatchException(CompletedMatchException ex) {
        return ResponseEntity.badRequest()
                .body(
                        MatchResponse.builder()
                                .responseResult(ResponseResult.MATCH_ALREADY_COMPLETED)
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest()
                .body(UserResponse.builder()
                        .responseResult(ResponseResult.USER_ALREADY_EXISTS)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(NoUserWithSuchEmailException.class)
    public ResponseEntity<LoginResponse> handleNoUserWithSuchEmailException(NoUserWithSuchEmailException ex) {
        return ResponseEntity.badRequest()
                .body(LoginResponse.builder()
                        .responseResult(ResponseResult.NOT_EXISTING_USER)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<LoginResponse> handelIncorrectPasswordException(IncorrectPasswordException ex) {
        return ResponseEntity.badRequest()
                .body(LoginResponse.builder()
                        .responseResult(ResponseResult.WRONG_PASSWORD)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException ex) {
        ex.printStackTrace();
        return ResponseEntity
                .badRequest()
                .body("Session token is invalid");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity
                .badRequest()
                .body(
                        UserResponse
                                .builder()
                                .responseResult(ResponseResult.INVALID_REQUEST_DATA)
                                .errors(errors)
                                .build()
                );
    }

    @ExceptionHandler(UserAlreadyRegisteredForTeamException.class)
    public ResponseEntity<TeamResponse> handleUserAlreadyRegisteredForTeamException(UserAlreadyRegisteredForTeamException ex) {
        return ResponseEntity
                .badRequest()
                .body(
                        TeamResponse
                                .builder()
                                .responseResult(ResponseResult.USER_ALREADY_REGISTERED_FOR_TEAM)
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(UserNotInTeamException.class)
    public ResponseEntity<TeamResponse> handleUserNotInTeamException(UserNotInTeamException ex) {
        return ResponseEntity
                .badRequest()
                .body(
                        TeamResponse
                                .builder()
                                .responseResult(ResponseResult.USER_NOT_IN_TEAM)
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(UserAlreadyRegisteredForAnotherTeamException.class)
    public ResponseEntity<TeamResponse> handleUserAlreadyRegisteredForAnotherTeamException(UserAlreadyRegisteredForAnotherTeamException ex) {
        return ResponseEntity
                .badRequest()
                .body(
                        TeamResponse
                                .builder()
                                .responseResult(ResponseResult.USER_ALREADY_REGISTERED_FOR_ANOTHER_TEAM)
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }

}
