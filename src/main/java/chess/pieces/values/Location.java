package chess.pieces.values;

import java.util.Objects;

public class Location {

    private final char x;
    private final int y;

    private Location(char x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Location of(char x, int y) {
        verifyCurrentLocation(x, y);
        return new Location(x, y);
    }

    public char getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private static void verifyCurrentLocation(char x, int y) {
        if (x < 'a' || x > 'h' || y < 1 || y > 8) {
            throw new IllegalArgumentException("Invalid Location Input");
        }
    }
}
