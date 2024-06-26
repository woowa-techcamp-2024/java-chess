package chess;

public class Position {
    private final int row;
    private final int col;

    public Position(String position) {
        if (position.length() != 2) throw new IllegalArgumentException("position length must be 2");
        col = position.charAt(0) - 'a';
        row = 8 - Integer.parseInt(position.substring(1));
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
