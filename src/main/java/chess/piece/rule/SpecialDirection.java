package chess.piece.rule;

import chess.piece.PieceColor;

import java.util.List;

public enum SpecialDirection implements Moveable {
    KNIGHT_NNE(1, 2),
    KNIGHT_NNW(-1, 2),
    KNIGHT_SSE(1, -2),
    KNIGHT_SSW(-1, -2),
    KNIGHT_EEN(2, 1),
    KNIGHT_EES(2, -1),
    KNIGHT_WWN(-2, 1),
    KNIGHT_WWS(-2, -1),
    PAWN_WHITE(0, 1),
    PAWN_BLACK(0, -1);

    private final int fileDegree;
    private final int rankDegree;

    SpecialDirection(final int fileDegree, final int rankDegree) {
        this.fileDegree = fileDegree;
        this.rankDegree = rankDegree;
    }

    public static List<Moveable> getKnightDirection() {
        return List.of(KNIGHT_NNE, KNIGHT_NNW, KNIGHT_SSE, KNIGHT_SSW, KNIGHT_EEN, KNIGHT_EES, KNIGHT_WWN, KNIGHT_WWS);
    }

    public static List<Moveable> getPawnDirection(final PieceColor color) {
        if(color == PieceColor.WHITE) {
            return List.of(PAWN_WHITE);
        }

        return List.of(PAWN_BLACK);
    }

    @Override
    public int getRankDegree() {
        return rankDegree;
    }

    @Override
    public int getFileDegree() {
        return fileDegree;
    }
}
