package java_chess.application;

import java.util.List;
import java_chess.chess.Board;
import java_chess.chess.pieces.Piece;
import java_chess.utils.StringUtils;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public void printBoard() {
        for (int i = Board.ROWS.length - 1; i >= 0; i--) {
            printRank(Board.ROWS[i], board.getPiecesWithRow(Board.ROWS[i]));
        }
        printColumn();
    }

    private void printRank(int row, List<Piece> pieces) {
        var sb = new StringBuilder();
        sb.append(row).append(" ");
        for (var piece : pieces) {
            sb.append(piece.getSymbol().getValue()).append(" ");
        }
        System.out.print(StringUtils.appendNewLine(sb.toString()));
    }

    private void printColumn() {
        var sb = new StringBuilder();
        sb.append("  ");
        for (char c : Board.COLS) {
            sb.append(c).append(" ");
        }
        System.out.print(StringUtils.appendNewLine(sb.toString()));
    }

}
