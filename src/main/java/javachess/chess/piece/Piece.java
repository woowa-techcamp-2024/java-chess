package javachess.chess.piece;

import javachess.chess.BoardContext;
import javachess.chess.Offset;

public abstract class Piece {

    private final Color color;

    private boolean moved;

    protected Piece() {
        this(Color.WHITE);
    }

    protected Piece(final Color color) {
        this.color = color;
        this.moved = false;
    }

    public boolean isBlack() {
        return isColor(Color.BLACK);
    }

    public boolean isWhite() {
        return isColor(Color.WHITE);
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    protected Color getColor() {
        return color;
    }

    public boolean notMoved() {
        return !moved;
    }

    public void setMoved() {
        moved = true;
    }

    public abstract double value();

    public boolean canMove(Offset offset, BoardContext context) {
        if (context.isColorAt(offset, getColor())) {
            return false;
        }
        return canMoveImpl(offset, context);
    }

    protected abstract boolean canMoveImpl(Offset offset, BoardContext context);

    protected boolean isEmptyUntil(Offset unit, Offset endExclusive, BoardContext context) {
        int scale = endExclusive.getScale(unit);
        for (int s = 1; s < scale; s++) {
            Offset offset = unit.multiply(s);
            if (!context.isEmptyAt(offset)) return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return isWhite() ? whiteRepresentation() : blackRepresentation();
    }

    protected abstract String whiteRepresentation();

    protected abstract String blackRepresentation();

    public enum Color {
        BLACK, WHITE;

        public Color opposite() {
            return switch (this) {
                case BLACK -> Color.WHITE;
                case WHITE -> Color.BLACK;
            };
        }
    }

}
