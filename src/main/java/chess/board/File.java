package chess.board;

import chess.exception.InvalidMoveException;

import java.util.Arrays;

public enum File {
    A(1), B(2), C(3), D(4),
    E(5), F(6), G(7), H(8);

    private final int index;


    File(final int index) {
        this.index = index;
    }

    public static File of(final int index) {
        return Arrays.stream(File.values()).filter(file -> file.index == index)
                .findAny()
                .orElseThrow(InvalidMoveException::new);
    }

    public static File of(final char index) {
        return Arrays.stream(File.values()).filter(file -> file.name().toLowerCase().charAt(0) == index)
                .findAny()
                .orElseThrow(InvalidMoveException::new);
    }

    public int getIndex() {
        return index;
    }
}
