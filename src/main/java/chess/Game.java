package chess;

import chess.board.Board;
import chess.board.BoardMaker;

public class Game {
    private Board board;

    public void newGame() {
        board = new Board(BoardMaker.standard());
    }

    public void move(String source , String target) {
        board.move(source, target);
    }

    public String print() {
        return board.print();
    }
}
