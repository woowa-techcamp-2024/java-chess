package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static pe.goblin.chess.domain.board.Board.MAX_COLS;
import static pe.goblin.chess.domain.board.Board.MAX_ROWS;

public class DefaultScoreEvaluator implements ScoreEvaluator {
    @Override
    public double evaluate(Piece.Color color, List<List<Piece>> pieces) {
        double score = getTotalScore(color, pieces);
        score = evaluateSuccessivePawn(color, score, pieces);
        return score;
    }

    @Override
    public List<Piece> orderPiecesByScore(Piece.Color color, boolean naturalOrder, List<List<Piece>> pieces) {
        List<Piece> result = new ArrayList<>();
        for (int row = 0; row <= MAX_ROWS; row++) {
            for (int col = 0; col <= MAX_COLS; col++) {
                Piece piece = pieces.get(row).get(col);
                if (piece.getColor() == color) {
                    result.add(piece);
                }
            }
        }
        result.sort(Comparator.comparing(p -> p.getType().getDefaultPoint()));
        return naturalOrder ? result : result.reversed();
    }


    private double getTotalScore(Piece.Color color, List<List<Piece>> pieces) {
        double score = 0.0;
        for (int row = 0; row <= MAX_ROWS; row++) {
            for (int col = 0; col <= MAX_COLS; col++) {
                Piece piece = pieces.get(row).get(col);
                if (piece.getColor() == color) {
                    score += piece.getType().getDefaultPoint();
                }
            }
        }
        return score;
    }

    private double evaluateSuccessivePawn(Piece.Color color, double score, List<List<Piece>> pieces) {
        for (int col = 0; col <= MAX_COLS; col++) {
            boolean isPawnBefore = false;
            int pawnInCol = 1;
            for (int row = 0; row <= MAX_ROWS; row++) {
                Piece piece = pieces.get(row).get(col);
                if (piece.getColor() == color && piece.getType() == Piece.Type.PAWN) {
                    if (isPawnBefore) {
                        pawnInCol++;
                    }
                    isPawnBefore = true;
                }
            }
            if (pawnInCol != 1) {
                score -= 0.5 * pawnInCol;
            }
        }
        return score;
    }
}
