package com.woowatechcamp.chess.pieces;

public enum Type {
    WHITE_PAWN('♙'),
    WHITE_KNIGHT('♘'),
    WHITE_BISHOP('♗'),
    WHITE_ROOK('♖'),
    WHITE_QUEEN('♕'),
    WHITE_KING('♔'),
    BLACK_PAWN('♟'),
    BLACK_KNIGHT('♞'),
    BLACK_BISHOP('♝'),
    BLACK_ROOK('♜'),
    BLACK_QUEEN('♛'),
    BLACK_KING('♚'),
    EMPTY('.');

    private char symbol;

    Type(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
