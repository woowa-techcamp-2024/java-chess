package java_chess.chess.pieces.enums;

import java_chess.chess.pieces.Bishop;
import java_chess.chess.pieces.King;
import java_chess.chess.pieces.Knight;
import java_chess.chess.pieces.Pawn;
import java_chess.chess.pieces.Piece;
import java_chess.chess.pieces.Queen;
import java_chess.chess.pieces.Rook;

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
