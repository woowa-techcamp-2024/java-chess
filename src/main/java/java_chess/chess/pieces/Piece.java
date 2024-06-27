package java_chess.chess.pieces;

import java_chess.chess.pieces.enums.Color;
import java_chess.chess.pieces.enums.Direction;
import java_chess.chess.pieces.enums.Symbol;
import java_chess.chess.pieces.enums.Type;

public abstract class Piece {

    protected final Color color;
    protected final Symbol symbol;
    protected final double score;

    protected Piece(Color color, Symbol symbol, double score) {
        this.color = color;
        this.symbol = symbol;
        this.score = score;
    }

    public boolean verifySameColor(Color color) {
        return this.color.equals(color);
    }

    public boolean isBlank() {
        return this instanceof Blank;
    }

    public Color getColor() {
        return color;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public double getScore() {
        return score;
    }

    public abstract Direction[] getDirections();

    public abstract boolean canMoveMultipleTimes();

    public static Piece generatePiece(Type type, Color color) {
        Piece piece = null;
        try {
            piece = type.getClazz().getDeclaredConstructor(Color.class).newInstance(color);
        } catch (Exception ignore) {
        }
        return piece;
    }

    public static Piece getBlank() {
        return Blank.INSTANCE;
    }

    @Override
    public String toString() {
        return symbol.getValue();
    }
}
