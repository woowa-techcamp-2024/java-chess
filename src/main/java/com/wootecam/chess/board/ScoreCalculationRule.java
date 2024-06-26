package com.wootecam.chess.board;

import static com.wootecam.chess.board.Board.MAX_COL;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.PieceType;
import java.util.Arrays;

public class ScoreCalculationRule {

    public double apply(Rank[] ranks, Color color) {
        return calculateScoreExceptPawn(ranks, color) + calculatePawnScore(ranks, color);
    }

    private double calculateScoreExceptPawn(Rank[] ranks, Color color) {
        return Arrays.stream(ranks)
                .flatMap(rank -> rank.getPieces(color).stream())
                .filter(piece -> piece.isColor(color) && !piece.isPawn())
                .mapToDouble(piece -> piece.getType().point)
                .sum();
    }

    private static double calculatePawnScoreInColumn(int count) {
        if (count == 1) {
            return PieceType.PAWN.point;
        }
        if (count >= 2) {
            return PieceType.PAWN.point * count / 2;
        }

        return 0;
    }

    private double calculatePawnScore(Rank[] ranks, Color color) {
        double score = 0;

        for (int col = 0; col < MAX_COL; ++col) {
            int count = countPawnsInColumn(ranks, color, col);
            score += calculatePawnScoreInColumn(count);
        }

        return score;
    }

    private int countPawnsInColumn(Rank[] ranks, Color color, int col) {
        return (int) Arrays.stream(ranks)
                .map(r -> r.get(col))
                .filter(p -> p.isColor(color) && p.isPawn())
                .count();
    }
}
