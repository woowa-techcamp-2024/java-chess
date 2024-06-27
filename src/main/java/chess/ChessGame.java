package chess;

import chess.board.Board;
import chess.board.Position;
import chess.exception.InvalidMoveException;
import chess.piece.PieceColor;
import chess.view.ChessView;

import static chess.exception.ExceptionConstant.INVALID_MOVE;

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
        if (!rule.isMove(Position.of(source), Position.of(target), turn)) {
            throw new InvalidMoveException(INVALID_MOVE);
        }

        board.move(Position.of(source), Position.of(target));

        return ChessView.showBoard(board);
    }

    public void end() {

    }

    //TODO: implement isCheckmate
    public boolean isCheckmate() {
        return false;
    }
}
