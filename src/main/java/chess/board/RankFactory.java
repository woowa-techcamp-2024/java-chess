package chess.board;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.PieceFactory;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.type.Color;

public final class RankFactory {
    private static final int RANK_SIZE = BoardSize.RANK.getSize();
    private static final int WHITE_PAWN_RANK = 6;
    private static final int WHITE_MAIN_RANK = 7;
    private static final int BLACK_PAWN_RANK = 1;
    private static final int BLACK_MAIN_RANK = 0;

    private RankFactory() {}

    public static Rank initWhitePawns() {
        return initPawns(Color.WHITE, WHITE_PAWN_RANK);
    }

    public static Rank initBlackPawns() {
        return initPawns(Color.BLACK, BLACK_PAWN_RANK);
    }

    public static Rank initWhiteGoodPieces() {
        return initGoodPieces(Color.WHITE, WHITE_MAIN_RANK);
    }

    public static Rank initBlackGoodPieces() {
        return initGoodPieces(Color.BLACK, BLACK_MAIN_RANK);
    }

    public static Rank initEmptyRank(int line) {
        Rank rank = new Rank();
        for (int i=0; i<RANK_SIZE; i++) {
            Piece empty = PieceFactory.createBlank(Position.from(i, line));
            rank.add(empty);
        }
        return rank;
    }

    private static Rank initPawns(Color color, int rankNumber) {
        Rank rank = new Rank();
        for (int i=0; i<RANK_SIZE; i++) {
            Piece pawn = Pawn.create(color, Position.from(i, rankNumber));
            rank.add(pawn);
        }
        return rank;
    }

    private static Rank initGoodPieces(Color color, int rankNumber) {
        Rank rank = new Rank();
        rank.add(Rook.create(color, Position.from(0, rankNumber)));
        rank.add(Knight.create(color, Position.from(1, rankNumber)));
        rank.add(Bishop.create(color, Position.from(2, rankNumber)));
        rank.add(Queen.create(color, Position.from(3, rankNumber)));
        rank.add(King.create(color, Position.from(4, rankNumber)));
        rank.add(Bishop.create(color, Position.from(5, rankNumber)));
        rank.add(Knight.create(color, Position.from(6, rankNumber)));
        rank.add(Rook.create(color, Position.from(7, rankNumber)));
        return rank;
    }
}
