package chess.board;

import chess.exception.InvalidMoveException;
import chess.piece.rule.Moveable;

import static chess.exception.ExceptionConstant.INVALID_MOVE;

public record Position(Rank rank, File file) {

    public static Position of(final int row, final int col) {
        return new Position(Rank.of(row), File.of(col));
    }

    public static Position of(final Rank rank, final File file) {
        return new Position(rank, file);
    }

    public static Position of(final String input) {
        File file = File.of(input.charAt(0));
        int rankValue = Character.getNumericValue(input.charAt(1));

        if (rankValue < 0) {
            throw new InvalidMoveException(INVALID_MOVE);
        }

        Rank rank = Rank.of(rankValue);
        return Position.of(rank, file);
    }

    public static Position of(final Position source, final Moveable direction, final int distance) {
        int rank = source.rank().getIndex() + direction.getRankDegree() * distance;
        int file = source.file().getIndex() + direction.getFileDegree() * distance;

        return Position.of(rank, file);
    }
}
