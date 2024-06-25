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

    @Override
    public final String toString() {
        return isWhite() ? whiteRepresentation() : blackRepresentation();
    }

    protected abstract String whiteRepresentation();

    protected abstract String blackRepresentation();

    public static Piece createBlack(Type type) {
        return create(type.type, Color.BLACK);
    }

    public static Piece createWhite(Type type) {
        return create(type.type, Color.WHITE);
    }

    private static <T extends Piece> T create(Class<T> type, Color color) {
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

    public enum Type {
        PAWN(Pawn.class),
        KNIGHT(Knight.class),
        BISHOP(Bishop.class),
        ROOK(Rook.class),
        QUEEN(Queen.class),
        KING(King.class);

        private final Class<? extends Piece> type;

        Type(Class<? extends Piece> type) {
            this.type = type;
        }
    }

}
