package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.stream.Collectors;
import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Piece;

public class BoardView {

    private static final String COLUMNS = "  a b c d e f g h";
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";

    private final Board board;

    public BoardView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (Rank row : board.getBoardOnlyRead()) {
            sb.append(appendNewLine(row.getPieces().stream()
                    .map(Piece::getRepresentation)
                    .collect(Collectors.joining())));
        }
        return sb.toString();
    }

    public String createDisplayBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(RED).append(COLUMNS).append(RESET).append("\n");
        int rowNumber = 8;
        for (Rank row : board.getBoardOnlyRead()) {
            sb.append(RED).append(rowNumber).append(RESET).append(" ");
            sb.append(row.getPieces().stream()
                    .map(Piece::getRepresentation)
                    .collect(Collectors.joining(" ")));
            sb.append(" ").append(RED).append(rowNumber).append(RESET).append("\n");
            rowNumber--;
        }
        sb.append(RED).append(COLUMNS).append(RESET);
        return sb.toString();
    }

    public void print() {
        System.out.println(createDisplayBoard());
    }
}
