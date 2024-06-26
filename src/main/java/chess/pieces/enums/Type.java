package chess.pieces.enums;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

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

    public static Type[] defaultOrder() {
        return new Type[]{ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK};
    }

    public Class<? extends Piece> getClazz() {
        return clazz;
    }

    public boolean isInstance(Piece piece) {
        return clazz.isInstance(piece);
    }
}
