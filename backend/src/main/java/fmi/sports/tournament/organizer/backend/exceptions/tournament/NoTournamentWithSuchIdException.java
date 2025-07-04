package fmi.sports.tournament.organizer.backend.exceptions.tournament;

public class NoTournamentWithSuchIdException extends RuntimeException {

    public NoTournamentWithSuchIdException(String msg) {
        super(msg);
    }

    public NoTournamentWithSuchIdException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
