package chess.exception;

public class InvalidPieceException extends IllegalArgumentException {
    public InvalidPieceException(final String message) {
        super(message);
    }
}
