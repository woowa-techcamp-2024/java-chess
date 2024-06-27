package chess.board;

import chess.pieces.PieceFactory;
import chess.pieces.Piece;

public final class RankFactory {
    private RankFactory() {}

    public static Rank initWhitePawns() {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = PieceFactory.createWhitePawn(Position.from(i, 6));
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank initBlackPawns() {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece pawn = PieceFactory.createBlackPawn(Position.from(i, 1));
            rank.add(pawn);
        }
        return rank;
    }

    public static Rank initEmptyRank(int line) {
        Rank rank = new Rank();
        for (int i=0; i<8; i++) {
            Piece empty = PieceFactory.createBlank(Position.from(i, line));
            rank.add(empty);
        }
        return rank;
    }

    public static Rank initWhiteGoodPieces() {
        Rank rank = new Rank();
        rank.add(PieceFactory.createWhiteRook(Position.from(0, 7)));
        rank.add(PieceFactory.createWhiteKnight(Position.from(1, 7)));
        rank.add(PieceFactory.createWhiteBishop(Position.from(2, 7)));
        rank.add(PieceFactory.createWhiteQueen(Position.from(3, 7)));
        rank.add(PieceFactory.createWhiteKing(Position.from(4, 7)));
        rank.add(PieceFactory.createWhiteBishop(Position.from(5, 7)));
        rank.add(PieceFactory.createWhiteKnight(Position.from(6, 7)));
        rank.add(PieceFactory.createWhiteRook(Position.from(7, 7)));
        return rank;
    }

    public static Rank initBlackGoodPieces() {
        Rank rank = new Rank();
        rank.add(PieceFactory.createBlackRook(Position.from(0, 7)));
        rank.add(PieceFactory.createBlackKnight(Position.from(1, 7)));
        rank.add(PieceFactory.createBlackBishop(Position.from(2, 7)));
        rank.add(PieceFactory.createBlackQueen(Position.from(3, 7)));
        rank.add(PieceFactory.createBlackKing(Position.from(4, 7)));
        rank.add(PieceFactory.createBlackBishop(Position.from(5, 7)));
        rank.add(PieceFactory.createBlackKnight(Position.from(6, 7)));
        rank.add(PieceFactory.createBlackRook(Position.from(7, 7)));
        return rank;
    }
}
