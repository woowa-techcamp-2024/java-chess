package chess.pieces;

import java.util.List;

public class Course {
    private List<Direction> directions;
    private boolean isRecursive;

    public Course(List<Direction> directions, boolean isRecursive) {
        this.directions = directions;
        this.isRecursive = isRecursive;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public boolean isRecursive() {
        return isRecursive;
    }
}
