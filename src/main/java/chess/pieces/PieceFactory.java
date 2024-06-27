package chess.pieces;

import chess.board.Position;
import chess.pieces.type.Color;

public class PieceFactory {
    private PieceFactory() {}

    public static Blank createBlank(Position position) {
        return Blank.create(position);
    }

    public static Bishop createWhiteBishop(Position position) {
        return Bishop.create(Color.WHITE, position);
    }

    public static Bishop createBlackBishop(Position position) {
        return Bishop.create(Color.BLACK, position);
    }

    public static Rook createWhiteRook(Position position) {
        return Rook.create(Color.WHITE, position);
    }

    public static Rook createBlackRook(Position position) {
        return Rook.create(Color.BLACK, position);
    }

    public static Knight createWhiteKnight(Position position) {
        return Knight.create(Color.WHITE, position);
    }

    public static Knight createBlackKnight(Position position) {
        return Knight.create(Color.BLACK, position);
    }

    public static Queen createWhiteQueen(Position position) {
        return Queen.create(Color.WHITE, position);
    }

    public static Queen createBlackQueen(Position position) {
        return Queen.create(Color.BLACK, position);
    }

    public static King createWhiteKing(Position position) {
        return King.create(Color.WHITE, position);
    }

    public static King createBlackKing(Position position) {
        return King.create(Color.BLACK, position);
    }

    public static Pawn createWhitePawn(Position position) {
        return Pawn.create(Color.WHITE, position);
    }

    public static Pawn createBlackPawn(Position position) {
        return Pawn.create(Color.BLACK, position);
    }
}
