package chess;

import static utils.StringUtils.appendNewLine;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public static final int BOARD_SIZE = 8;
    private final List<Rank> boards = new ArrayList<>();

    public void initialize() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            boards.add(new Rank(i));
        }
    }

    public void print() {
        System.out.println(this);
    }

    public int size() {
        return boards.size();
    }

    public int pieceCount() {
        int result = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            result += boards.get(i).getPieceCount();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : boards) {
            stringBuilder.append(appendNewLine(rank.toString()));
        }
        return stringBuilder.toString();
    }
}
