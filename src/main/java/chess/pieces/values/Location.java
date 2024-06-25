package chess.pieces.values;

import java.util.Objects;
import java.util.regex.Pattern;

public class Location {

    private static final Pattern PATTERN = Pattern.compile("[a-h][1-8]");

    private final int x;
    private final char y;

    private Location(int x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Location from(String location) {
        if (!PATTERN.matcher(location).matches()) {
            throw new IllegalArgumentException("Invalid Location Input");
        }
        var x = Integer.parseInt(location.substring(1));
        var y = location.charAt(0);
        return new Location(x, y);
    }

    public static Location of(int x, char y) {
        verifyCurrentLocation(x, y);
        return new Location(x, y);
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

    private static void verifyCurrentLocation(int x, char y) {
        if (x < 1 || x > 8 || y < 'a' || y > 'h') {
            throw new IllegalArgumentException("Invalid Location Input");
        }
    }
}
