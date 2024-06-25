package com.seong.chess.pieces;

import java.util.HashMap;
import java.util.Map;

public class Pawn extends Piece {

    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';
    private static final Map<String, Character> COLORS_CHARACTER = new HashMap<>();

    static {
        COLORS_CHARACTER.put(Colors.WHITE_COLOR, WHITE_REPRESENTATION);
        COLORS_CHARACTER.put(Colors.BLACK_COLOR, BLACK_REPRESENTATION);
    }

    private final char representation;

    public Pawn() {
        super(Colors.WHITE_COLOR);
        this.representation = WHITE_REPRESENTATION;
    }

    public Pawn(String color) {
        super(color);
        this.representation = COLORS_CHARACTER.get(color);
    }

    public Pawn(String color, char representation) {
        super(color);
        this.representation = representation;
    }

    @Override
    public char getRepresentation() {
        return representation;
    }
}
