package javachess;

import javachess.chess.Position;

public class StateDto {

    public String message;
    public String checkPosition;
    public boolean checkmate;

    public StateDto(String message, String checkPosition, boolean checkmate) {
        this.message = message;
        this.checkPosition = checkPosition;
        this.checkmate = checkmate;
    }

    public static StateDto win(String message) {
        return new StateDto(message, "", true);
    }

    public static StateDto playing(String message, String checkPosition) {
        return new StateDto(message, checkPosition, false);
    }
}
