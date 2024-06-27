package chess.board;

public record Position(Rank rank, File file) {

    public static Position of(final int row, final int col) {
        return new Position(Rank.of(row), File.of(col));
    }

    public static Position of(final Rank rank, final File file) {
        return new Position(rank, file);
    }
}
