package chess;

import exception.OutOfBoardException;

import java.util.Objects;

public class Position {
    int x;
    int y;

    public Position(String position){
        char colChar = position.charAt(0);
        char rowChar = position.charAt(1);
        int x = colChar - 'a';
        int y = 8-Character.getNumericValue(rowChar);
        validPosition(y, x);
        this.x = x;
        this.y = y;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    private void validPosition(int y, int x){
        if(!(0 <= y && y < BoardArea.Y.getMax() && 0 <= x && x < BoardArea.X.getMax())){
            throw new OutOfBoardException("체스판 범위를 벗어나는 입력입니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position(" + (char) (x + 'a') + (8-y) + ")";
    }
}
