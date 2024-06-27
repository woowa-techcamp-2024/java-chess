package com.seong.chess;

import com.seong.chess.pieces.Piece.Color;

public class Turn {
    private Color color;

    private Turn(Color color) {
        this.color = color;
    }

    public static Turn start() {
        return new Turn(Color.WHITE);
    }

    public void change() {
        color = color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Color getColor() {
        return color;
    }
}
