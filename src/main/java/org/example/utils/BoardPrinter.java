package org.example.utils;

import static org.example.utils.StringUtils.appendNewLine;

import java.util.List;
import org.example.chess.Board;
import org.example.chess.Column;

public class BoardPrinter {

    public static String showBoard(Board board) {
        int boardSize = board.getColumns().size();
        List<Column> columns = board.getColumns();

        StringBuilder sb = new StringBuilder();
        // row를 구하면 각 col마다 해당 row의 piece를 가져와서 출력한다.
        for (int i = boardSize; i > 0; i--) {
            sb.append(appendNewLine(getRow(i, columns)));
        }
        return sb.toString();
    }

    private static String getRow(int row, List<Column> columns) {
        StringBuilder sb = new StringBuilder();
        for (Column column : columns) {
            sb.append(column.getPiece(row).getRepresentation());
        }
        return sb.toString();
    }
}
