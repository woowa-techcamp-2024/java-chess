package utils;

import chess.Rank;
import chess.pieces.Color;
import chess.pieces.Piece;
import chess.pieces.Representation;

public final class RankMaker {
    private RankMaker() {}

    public static Rank getPawnsRank(Color color) {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Representation.Type.PAWN, color);
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank getEmptyRank() {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = Piece.create(Representation.Type.NO_PIECE, Color.NOCOLOR);
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank getGoodPiecesRank(Color color) {
        Rank rank = new Rank();
        rank.add(Piece.create(Representation.Type.ROOK, color));
        rank.add(Piece.create(Representation.Type.KNIGHT, color));
        rank.add(Piece.create(Representation.Type.BISHOP, color));
        rank.add(Piece.create(Representation.Type.QUEEN, color));
        rank.add(Piece.create(Representation.Type.KING, color));
        rank.add(Piece.create(Representation.Type.BISHOP, color));
        rank.add(Piece.create(Representation.Type.KNIGHT, color));
        rank.add(Piece.create(Representation.Type.ROOK, color));
        return rank;
    }
}
