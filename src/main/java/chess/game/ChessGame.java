package chess.game;

import chess.board.Board;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public Board getBoard(){
        return board;
    }

    public void move(String sourcePosition, String targetPosition) {
        board.move(sourcePosition, targetPosition);
    }

}
