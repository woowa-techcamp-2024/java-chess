package chess.piece;

import java.lang.reflect.Constructor;

public abstract class Piece {

    private final Color color;

    protected Piece() {
        this(Color.WHITE);
    }

    protected Piece(final Color color) {
        this.color = color;
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

    public abstract double value();

    @Override
    public final String toString() {
        return isWhite() ? whiteRepresentation() : blackRepresentation();
    }

    protected abstract String whiteRepresentation();

    protected abstract String blackRepresentation();

    public static <T extends Piece> T createBlack(Class<T> type) {
        return create(type, Color.BLACK);
    }

    public static <T extends Piece> T createWhite(Class<T> type) {
        return create(type, Color.WHITE);
    }

    public static <T extends Piece> T create(Class<T> type, Color color) {
        try {
            Constructor<T> constructor = type.getDeclaredConstructor(Color.class);
            constructor.setAccessible(true);
            return constructor.newInstance(color);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public enum Color {
        BLACK, WHITE
    }

}
