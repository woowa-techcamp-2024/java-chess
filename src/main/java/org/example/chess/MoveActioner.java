package org.example.chess;

import org.example.chess.pieces.global.Position;

public class MoveActioner {
    private Board board;

    public MoveActioner(Board board) {
        this.board = board;
    }

    public void moveTo(Position from, Position to) throws RuntimeException{
        board.moveTo(from, to);
    }

}
