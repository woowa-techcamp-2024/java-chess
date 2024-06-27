package chess;

import chess.board.Board;
import chess.board.Position;
import chess.view.ChessView;

public class ChessGame {

    private final Board board;

    public ChessGame(final Board board) {
        this.board = board;
    }

    public String start() {
        board.initialize();

        return ChessView.showBoard(board);
    }

    public String play(final String source, final String target) {
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
