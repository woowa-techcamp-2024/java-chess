package chess.pieces;

import chess.Position;
import chess.constant.Color;
import chess.constant.Type;

public class PieceFactory {
    public static Piece createMovedPiece(final Piece piece, final Position position) {
        Piece result = null;
        switch (piece.getType()) {
            case Type.PAWN -> result = createPawn(piece.getColor(), position);
            case Type.KNIGHT -> result = createKnight(piece.getColor(), position);
            case Type.ROOK -> result = createRook(piece.getColor(), position);
            case Type.BISHOP -> result = createBishop(piece.getColor(), position);
            case Type.QUEEN -> result = createQueen(piece.getColor(), position);
            case Type.KING -> result = createKing(piece.getColor(), position);
            case Type.NO_PIECE -> result = createBlank(position);
        }
        return result;
    }

    public static Piece createPawn(final Color color, final Position position) {
        return Pawn.create(color, position);
    }

    public static Piece createKnight(final Color color, final Position position) {
        return Knight.create(color, position);
    }

    public static Piece createRook(final Color color, final Position position) {
        return Rook.create(color, position);
    }

    public static Piece createBishop(final Color color, final Position position) {
        return Bishop.create(color, position);
    }

    public static Piece createQueen(final Color color, final Position position) {
        return Queen.create(color, position);
    }

    public static Piece createKing(final Color color, final Position position) {
        return King.create(color, position);
    }

    public static Piece createBlank(final Position position) { return Blank.create(position); }

}
