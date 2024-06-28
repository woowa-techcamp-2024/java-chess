package chess.piece.rule;

import java.util.List;

public record PieceMove(List<Moveable> directions, int distance) {
    public static PieceMove of(List<Moveable> directions, int distance) {
        return new PieceMove(directions, distance);
    }
}
