package javachess.chess;

import java.util.List;
import java.util.Objects;

public class Offset {
    public static final Offset NORTH = new Offset(1, 0);
    public static final Offset NORTHEAST = new Offset(1, 1);
    public static final Offset EAST = new Offset(0, 1);
    public static final Offset SOUTHEAST = new Offset(-1, 1);
    public static final Offset SOUTH = new Offset(-1, 0);
    public static final Offset SOUTHWEST = new Offset(-1, -1);
    public static final Offset WEST = new Offset(0, -1);
    public static final Offset NORTHWEST = new Offset(1, -1);

    public static final Offset NNE = new Offset(2, 1);
    public static final Offset NNW = new Offset(2, -1);
    public static final Offset SSE = new Offset(-2, 1);
    public static final Offset SSW = new Offset(-2, -1);
    public static final Offset EEN = new Offset(1, 2);
    public static final Offset EES = new Offset(-1, 2);
    public static final Offset WWN = new Offset(1, -2);
    public static final Offset WWS = new Offset(-1, -2);

    public static final List<Offset> LINEAR = List.of(NORTH, EAST, SOUTH, WEST);
    public static final List<Offset> DIAGONAL = List.of(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    public static final List<Offset> EVERY = List.of(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    public static final List<Offset> KNIGHT = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);

    private final int rank;
    private final int file;

    protected Offset(int rank, int file) {
        this.rank = rank;
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public int getFile() {
        return file;
    }

    public Offset multiply(int value) {
        return new Offset(rank * value, file * value);
    }

    /**
     * 이 Offset이 다른 Offset의 정수배인지 여부를 반환합니다.
     *
     * @param other
     * @return
     */
    public boolean isMultipleOf(Offset other) {
        if (Integer.signum(rank) != Integer.signum(other.rank)) return false;
        if (Integer.signum(file) != Integer.signum(other.file)) return false;
        if (other.rank == 0) return file % other.file == 0;
        if (other.file == 0) return rank % other.rank == 0;
        return rank % other.rank == 0 && file % other.file == 0 && rank / other.rank == file / other.file;
    }

    /**
     * 이 Offset의 다른 Offset에 대한 크기를 반환합니다.
     *
     * @param other
     * @return
     */
    public int getScale(Offset other) {
        if (!isMultipleOf(other)) {
            throw new IllegalArgumentException("Not multiple of " + other);
        }
        if (other.rank == 0 && other.file == 0) {
            throw new IllegalArgumentException("Other offset is zero");
        }
        if (other.rank != 0) {
            return rank / other.rank;
        }
        return file / other.file;
    }

    public static Offset between(Position from, Position to) {
        return new Offset(to.rankIndex - from.rankIndex, to.fileIndex - from.fileIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offset offset = (Offset) o;
        return rank == offset.rank && file == offset.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

}
