package chess.pieces;

import chess.CommandChanger;
import chess.Position;

import java.util.*;
import java.util.stream.IntStream;

public abstract class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('p', 1.0), KNIGHT('n', 2.5), BISHOP('b', 3.0), ROOK('r', 5.0), QUEEN('q', 9.0), KING('k', 0.0), NO_PIECE('.', 0.0);

        private final char representation;
        private final double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getRepresentation(Color color) {
            if (Objects.equals(color, Color.NOCOLOR)) {
                return Type.NO_PIECE.representation;
            }

            if (color == Color.BLACK) {
                return Character.toUpperCase(representation);
            }

            return representation;
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }
    }

    private final Color color;
    private char representation;
    private Position position;
    private double defaultPoint;

    protected Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    protected Piece(final Color color, final char representation, final Position position, final double defaultPoint) {
        this.color = color;
        this.representation = representation;
        this.position = position;
        this.defaultPoint = defaultPoint;
    }


    public static Piece of(Class<? extends Piece> pieceClass, Color color) {
        try {
            return pieceClass.getDeclaredConstructor(Color.class, Position.class).newInstance(color, null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Piece class: " + pieceClass.getName());
        }
    }

    public static Piece of(Class<? extends Piece> pieceClass, Color color, Position position) {
        try {
            return pieceClass.getDeclaredConstructor(Color.class, Position.class).newInstance(color, position);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Piece class: " + pieceClass.getName());
        }
    }

    public static Piece of(Class<? extends Piece> pieceClass, Color color, String position) {
        try {
            return pieceClass.getDeclaredConstructor(Color.class, Position.class).newInstance(color, CommandChanger.getPosition(position));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Piece class: " + pieceClass.getName());
        }
    }

    public Color getColor() {
        return color;
    }

    public double getDefaultScore() {
        return defaultPoint;
    }

    public char getType() {
        return representation;
    }

    public boolean isBlack() {
        return Objects.equals(color, Color.BLACK);
    }

    public boolean isWhite() {
        return Objects.equals(color, Color.WHITE);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPosition(String position) {
        this.position = CommandChanger.getPosition(position);
    }

    public void move(String source, String target) {
        Position sourcePos = CommandChanger.getPosition(source);
        Position targetPos = CommandChanger.getPosition(target);
        if (!getDirs().contains(Position.calculateDistance(sourcePos, targetPos))) {
            throw new IllegalArgumentException("Error: 움직일 수 없는 명령입니다.");
        }

        setPosition(target);
    }

    public void setMoved() {}

    public boolean isMoved() {
        return false;
    }

    protected Set<Position> getDirs() {
        return Set.of();
    }


    public Position findDir(Position source, Position target) {
        List<Position> dirs = getDirs().stream().toList();
        List<Double> distances = new ArrayList<>();

        for (Position dir : dirs) {
            distances.add(source.add(dir).calcDistance(target));
        }

        // find min in distance
        return dirs.get(IntStream.range(0, distances.size())
                .boxed()
                .min(Comparator.comparing(distances::get))
                .orElseThrow(() -> new IllegalArgumentException("Error: 움직일 수 있는 방향이 없습니다.")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return representation == piece.representation && Double.compare(defaultPoint, piece.defaultPoint) == 0 && color == piece.color && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, representation, position, defaultPoint);
    }
}
