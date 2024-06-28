package org.example.chess;

import org.example.pieces.Piece.Color;

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

    public void move(Position start, Position end, Color turn) {
        board.move(start, end, turn);
    }

    public void move(String start, String end, Color turn) {
        this.move(new Position(start), new Position(end), turn);
    }
}
