package chess.exception;

public class InvalidMoveException extends IllegalArgumentException {

    public InvalidMoveException() {
        super("이동할 수 없는 위치입니다.");
    }

}
