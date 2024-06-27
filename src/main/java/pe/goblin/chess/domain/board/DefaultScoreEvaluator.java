package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.vo.Color;
import pe.goblin.chess.domain.piece.vo.PieceType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DefaultScoreEvaluator implements ScoreEvaluator {
    @Override
    public double evaluate(Color color, Board board) {
        double score = getTotalScore(color, board.getPieces());
        score = evaluateSuccessivePawn(color, score, board.getPieces());
        return score;
    }

    private double getTotalScore(Color color, List<List<Piece>> pieces) {
        double score = 0.0;
        for (List<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                if (piece.getColor() == color) {
                    score += piece.getType().getDefaultPoint();
                }
            }
        }
        return score;
    }

    private double evaluateSuccessivePawn(Color color, double score, List<List<Piece>> pieces) {
        for (int col = 0; col < pieces.getFirst().size(); col++) {
            boolean isPawnBefore = false;
            int pawnInCol = 1;
            for (List<Piece> pieceList : pieces) {
                Piece piece = pieceList.get(col);
                if (piece.getColor() == color && piece.getType() == PieceType.PAWN) {
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

    @Override
    public List<Piece> orderPiecesByScore(Color color, boolean naturalOrder, List<List<Piece>> pieces) {
        List<Piece> result = new ArrayList<>();
        for (List<Piece> pieceList : pieces) {
            for (Piece piece : pieceList) {
                if (piece.getColor() == color) {
                    result.add(piece);
                }
            }
        }
        result.sort(Comparator.comparing(p -> p.getType().getDefaultPoint()));
        return naturalOrder ? result : result.reversed();
    }
}
