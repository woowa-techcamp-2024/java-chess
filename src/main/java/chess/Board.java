package chess;

import static pieces.Color.BLACK;
import static pieces.Color.NONE;
import static pieces.Color.WHITE;

import java.util.ArrayList;
import java.util.List;
import pieces.Empty;
import pieces.Pawn;
import pieces.Piece;

public class Board {

    public static final char EMPTY = '.';
    public static final int BOARD_SIZE = 8;
    private final List<List<Piece>> boards = new ArrayList<>();

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boards.add(new ArrayList<>());
            for (int j = 0; j < BOARD_SIZE; j++) {
                addPieceToBoard(i);
            }
        }
    }

    public void print() {
        System.out.println(this);
    }

    public int size() {
        return boards.size();
    }

    public String getWhitePawnsResult() {
        return "p".repeat(BOARD_SIZE);
    }

    public String getBlackPawnsResult() {
        return "P".repeat(BOARD_SIZE);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < BOARD_SIZE; j++) {
                line.append(boards.get(i).get(j).getRepresentation());
            }
            result.append(line).append('\n');
        }
        return result.toString();
    }

    private void addPieceToBoard(int i) {
        if (i == 1) {
            boards.get(i).add(new Pawn(BLACK, Pawn.BLACK_REPRESENTATION));
        }
        if (i == 6) {
            boards.get(i).add(new Pawn(WHITE, Pawn.WHITE_REPRESENTATION));
        }
        boards.get(i).add(new Empty(NONE, EMPTY));
    }
}
