package org.example.chess.board;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.stream.Collectors;
import org.example.chess.board.Board.Rank;
import org.example.chess.pieces.Piece;

public class BoardView {

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

    public void print() {
        System.out.println(showBoard());
    }
}
