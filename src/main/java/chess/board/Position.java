package chess.board;

import java.util.Arrays;

public enum Position {
    NO_POSITION(-1, -1),
    A8(0, 0),
    A7(0, 1),
    A6(0, 2),
    A5(0, 3),
    A4(0, 4),
    A3(0, 5),
    A2(0, 6),
    A1(0, 7),
    B8(1, 0),
    B7(1, 1),
    B6(1, 2),
    B5(1, 3),
    B4(1, 4),
    B3(1, 5),
    B2(1, 6),
    B1(1, 7),
    C8(2, 0),
    C7(2, 1),
    C6(2, 2),
    C5(2, 3),
    C4(2, 4),
    C3(2, 5),
    C2(2, 6),
    C1(2, 7),
    D8(3, 0),
    D7(3, 1),
    D6(3, 2),
    D5(3, 3),
    D4(3, 4),
    D3(3, 5),
    D2(3, 6),
    D1(3, 7),
    E8(4, 0),
    E7(4, 1),
    E6(4, 2),
    E5(4, 3),
    E4(4, 4),
    E3(4, 5),
    E2(4, 6),
    E1(4, 7),
    F8(5, 0),
    F7(5, 1),
    F6(5, 2),
    F5(5, 3),
    F4(5, 4),
    F3(5, 5),
    F2(5, 6),
    F1(5, 7),
    G8(6, 0),
    G7(6, 1),
    G6(6, 2),
    G5(6, 3),
    G4(6, 4),
    G3(6, 5),
    G2(6, 6),
    G1(6, 7),
    H8(7, 0),
    H7(7, 1),
    H6(7, 2),
    H5(7, 3),
    H4(7, 4),
    H3(7, 5),
    H2(7, 6),
    H1(7, 7);

    private final int file;
    private final int rank;

    Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String position) {
        char x = position.charAt(0);
        int file = x - 'a';
        int rank = 8 - Integer.parseInt(position.substring(1));

        return from(file, rank);
    }

    public static Position from(int file, int rank) {
        return Arrays.stream(values())
                .filter(p -> p.file == file && p.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No position for " + file + ", " + rank));
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
