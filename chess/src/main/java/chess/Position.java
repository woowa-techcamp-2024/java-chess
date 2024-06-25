package chess;

public class Position {
    int rowIdx;
    int colIdx;

    public Position(String position)
    {
        char colChar = position.charAt(0);
        char rowChar = position.charAt(1);
        this.colIdx = colChar - 'a';
        this.rowIdx = 8-Character.getNumericValue(rowChar);
    }
    public int getRowIdx() {
        return rowIdx;
    }

    public int getColIdx() {
        return colIdx;
    }
}
