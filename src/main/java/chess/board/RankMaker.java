package chess.board;

import chess.pieces.type.Color;
import chess.pieces.Piece;
import chess.pieces.type.Type;

public final class RankMaker {
    private RankMaker() {}

    public static Rank getPawnsRank(Color color) {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Type.PAWN, color);
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank getEmptyRank() {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Type.NO_PIECE, Color.NOCOLOR);
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank getGoodPiecesRank(Color color) {
        Rank rank = new Rank();
        rank.add(Piece.create(Type.ROOK, color));
        rank.add(Piece.create(Type.KNIGHT, color));
        rank.add(Piece.create(Type.BISHOP, color));
        rank.add(Piece.create(Type.QUEEN, color));
        rank.add(Piece.create(Type.KING, color));
        rank.add(Piece.create(Type.BISHOP, color));
        rank.add(Piece.create(Type.KNIGHT, color));
        rank.add(Piece.create(Type.ROOK, color));
        return rank;
    }
}
