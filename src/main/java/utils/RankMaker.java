package utils;

import chess.Rank;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representations;

public final class RankMaker {
    private RankMaker() {}

    public static Rank getPawnsRank(Color color) {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Representations.Type.PAWN, color);
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank getEmptyRank() {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Representations.Type.NO_PIECE, Color.NOCOLOR);
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank getGoodPiecesRank(Color color) {
        Rank rank = new Rank();
        rank.add(Piece.create(Representations.Type.ROOK, color));
        rank.add(Piece.create(Representations.Type.KNIGHT, color));
        rank.add(Piece.create(Representations.Type.BISHOP, color));
        rank.add(Piece.create(Representations.Type.QUEEN, color));
        rank.add(Piece.create(Representations.Type.KING, color));
        rank.add(Piece.create(Representations.Type.BISHOP, color));
        rank.add(Piece.create(Representations.Type.KNIGHT, color));
        rank.add(Piece.create(Representations.Type.ROOK, color));
        return rank;
    }
}
