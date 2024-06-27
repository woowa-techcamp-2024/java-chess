package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.piece.Piece;

import java.util.List;

public interface ScoreEvaluator {
    double evaluate(Piece.Color color, List<List<Piece>> pieces);

    List<Piece> orderPiecesByScore(Piece.Color color, boolean naturalOrder, List<List<Piece>> pieces);
}
