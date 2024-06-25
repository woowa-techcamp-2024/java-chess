package chess.pieces.values;

import java.util.Objects;

public class Location {

    private final int x;
    private final int y;

    private Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Location of(int x, char y) {
        verifyCurrentLocation(x, y);
        return new Location(x, convertIdxToInt(y));
    }

    public int getX() {
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

    private static int convertIdxToInt(char y) {
        return y - 'a';
    }

    private static void verifyCurrentLocation(int x, char y) {
        if (x < 1 || x > 8 || y < 'a' || y > 'h') {
            throw new IllegalArgumentException("Invalid Location Input");
        }
    }
}
