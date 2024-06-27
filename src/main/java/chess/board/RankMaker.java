package chess.board;

import chess.pieces.type.Color;
import chess.pieces.Piece;
import chess.pieces.type.Type;

public final class RankMaker {
    private RankMaker() {}

    public static Rank initWhitePawns() {
        Rank rank = new Rank();
        Color color = Color.WHITE;

        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Type.PAWN, color, Position.from(i, 6));
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank initBlackPawns() {
        Rank rank = new Rank();
        Color color = Color.BLACK;

        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Type.PAWN, color, Position.from(i, 1));
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank initEmptyRank(int line) {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece empty = Piece.create(Type.NO_PIECE, Color.NOCOLOR, Position.from(i, line));
            rank.add(empty);
        }
        return rank;
    }

    public static Rank initWhiteGoodPieces() {
        Rank rank = new Rank();
        Color color = Color.WHITE;

        int i = 0;
        rank.add(Piece.create(Type.ROOK, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.KNIGHT, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.BISHOP, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.QUEEN, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.KING, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.BISHOP, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.KNIGHT, color, Position.from(i++, 7)));
        rank.add(Piece.create(Type.ROOK, color, Position.from(i++, 7)));
        return rank;
    }

    public static Rank initBlackGoodPieces() {
        Rank rank = new Rank();
        Color color = Color.BLACK;

        int i = 0;
        rank.add(Piece.create(Type.ROOK, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.KNIGHT, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.BISHOP, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.QUEEN, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.KING, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.BISHOP, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.KNIGHT, color, Position.from(i++, 0)));
        rank.add(Piece.create(Type.ROOK, color, Position.from(i++, 0)));
        return rank;
    }
}
