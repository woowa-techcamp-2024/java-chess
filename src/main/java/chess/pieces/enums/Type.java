package chess.pieces.enums;

import chess.pieces.*;

public enum Type {
    PAWN(Pawn.class),
    BISHOP(Bishop.class),
    KNIGHT(Knight.class),
    ROOK(Rook.class),
    QUEEN(Queen.class),
    KING(King.class);

    private final Class<? extends Piece> clazz;

    Type(Class<? extends Piece> clazz) {
        this.clazz = clazz;
    }

    public boolean isInstance(Piece piece) {
        return clazz.isInstance(piece);
    }
}
