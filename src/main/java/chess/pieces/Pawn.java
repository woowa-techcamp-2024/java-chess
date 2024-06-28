package chess.pieces;

import chess.board.Position;

import java.util.List;

public class Pawn extends Piece{

    private static List<Direction> directions;
    private final static int INIT_WHITE_PAWN_RANK = 6;
    private final static int INIT_BLACK_PAWN_RANK = 1;

    public Pawn(Color color, Type type) {
        super(color, type);
        directions = isWhite() ? Direction.whitePawnDirection() : Direction.blackPawnDirection();
    }

    @Override
    public boolean verifyMovePosition(String source, String target) {
        if(this.isBlack()) return verifyBlackPawnMovePosition(source, target);
        return verifyWhitePawnMovePosition(source, target);
    }

    public static Pawn createWhitePawn() {
        return new Pawn(Color.WHITE, Type.PAWN);
    }

    public static Pawn createBlackPawn(){
        return new Pawn(Color.BLACK, Type.PAWN);
    }

    private boolean verifyWhitePawnMovePosition(String source, String target) {

        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        int dx = sourcePosition.getFile() - targetPosition.getFile();
        int dy = sourcePosition.getRank() - targetPosition.getRank();

        if(Math.abs(dy) == 1)
            return true;

        if(sourcePosition.getRank() == INIT_WHITE_PAWN_RANK && Math.abs(dy) == 2)
            return true;

        return false;
    }

    private boolean verifyBlackPawnMovePosition(String source, String target) {

        Position sourcePosition = new Position(source);
        Position targetPosition = new Position(target);

        int dx = sourcePosition.getFile() - targetPosition.getFile();
        int dy = sourcePosition.getRank() - targetPosition.getRank();

        if(Math.abs(dy) == 1)
            return true;
        if(sourcePosition.getRank() == INIT_BLACK_PAWN_RANK && Math.abs(dy) == 2)
            return true;

        return false;
    }

}
