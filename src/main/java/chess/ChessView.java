package chess;

import chess.pieces.Rank;

import java.util.List;

import static chess.Board.BOARD_SIZE;
import static chess.utils.StringUtils.appendNewLine;

public class ChessView {
    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        List<Rank> ranks = board.findAll();
        StringBuilder printResult = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            String line = appendNewLine(ranks.get(i).showRank());
            printResult.append(line);
        }
        return printResult.toString();
    }
}
