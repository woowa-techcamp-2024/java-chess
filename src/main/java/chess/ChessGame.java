package chess;

import chess.board.Board;
import chess.board.Position;
import chess.piece.PieceColor;
import chess.view.ChessView;

public class ChessGame {

    private final Board board;
    private final Rule rule;

    public ChessGame(final Board board, final Rule rule) {
        this.board = board;
        this.rule = rule;
    }

    public String start() {
        board.initialize();

        return ChessView.showBoard(board);
    }

    public String play(final String source, final String target, final PieceColor turn) {
        Position from = Position.of(source);
        Position to = Position.of(target);
        rule.move(from, to, turn);

        return ChessView.showBoard(board);
    }

    public boolean isCheckmate(PieceColor turn) {
        return rule.isCheckmate(turn);
    }

    public boolean isPromotion(final String target) {
        return rule.isPromotion(Position.of(target));
    }

    public void promotion(final String target, final String piece) {
        rule.promotion(Position.of(target), piece);
    }

    public boolean isCheck(final PieceColor turn) {
        return rule.isCheck(turn);
    }
}
