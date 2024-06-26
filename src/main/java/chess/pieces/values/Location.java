package chess.pieces.values;

import java.util.Objects;
import java.util.regex.Pattern;

public class Location {

    private static final Pattern PATTERN = Pattern.compile("[a-h][1-8]");

    private final int x;
    private final int y;

    private Location(int x, char y) {
        this.x = x;
        this.y = charToInt(y);
    }

    /**
     * 위치를 입력받아 Location 객체를 생성합니다.
     *
     * @param location 위치
     * @return Location 객체
     * @throws IllegalArgumentException 위치가 잘못된 경우
     */
    public static Location from(String location) {
        if (!PATTERN.matcher(location).matches()) {
            throw new IllegalArgumentException("Invalid Location Input");
        }
        var x = Integer.parseInt(location.substring(1));
        var y = location.charAt(0);
        return new Location(x, y);
    }

    /**
     * x, y 좌표를 입력받아 Location 객체를 생성합니다.
     *
     * @param x x 좌표
     * @param y y 좌표
     * @return Location 객체
     * @throws IllegalArgumentException 위치가 잘못된 경우
     */
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

    public char getCurrentY() {
        return intToChar(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Location location)) {
            return false;
        }
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return intToChar(y) + "" + x;
    }

    private int charToInt(char y) {
        return y - 'a' + 1;
    }

    private char intToChar(int y) {
        return (char) (y + 'a' - 1);
    }

    private static void verifyCurrentLocation(int x, char y) {
        if (x < 1 || x > 8 || y < 'a' || y > 'h') {
            throw new IllegalArgumentException("Invalid Location Input");
        }
    }
}
