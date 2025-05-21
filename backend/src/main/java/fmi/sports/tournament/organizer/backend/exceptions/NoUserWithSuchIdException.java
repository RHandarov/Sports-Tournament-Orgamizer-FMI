package fmi.sports.tournament.organizer.backend.exceptions;

public class NoUserWithSuchIdException extends RuntimeException {
    public NoUserWithSuchIdException(String msg) {
        super(msg);
    }

    public NoUserWithSuchIdException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
