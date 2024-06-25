package chess.piece;

import chess.File;
import chess.Rank;

public record Position(File file, Rank rank) {

    public static Position of(final int row, final int col) {
        return new Position(File.of(col), Rank.of(row));
    }

    public static Position of(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
