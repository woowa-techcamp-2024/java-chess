package pe.goblin.chess.domain.board;

import pe.goblin.chess.domain.piece.Piece;
import pe.goblin.chess.domain.piece.vo.Color;

import java.util.List;

public interface ScoreEvaluator {
    double evaluate(Color color, List<List<Piece>> pieces);

    List<Piece> orderPiecesByScore(Color color, boolean naturalOrder, List<List<Piece>> pieces);
}
