package org.example.chess;

import org.example.pieces.Piece;

public class ChessGame {

    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    //게임 설정
    public void initialize() {
        board.initialize();
    }

    public void move(Position start, Position end) {
        board.move(start, end);
    }

    public void move(String start, String end) {
        this.move(new Position(start), new Position(end));
    }

}
