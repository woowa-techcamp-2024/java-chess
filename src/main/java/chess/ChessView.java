package chess;

import chess.pieces.Piece;
import java.util.List;
import utils.StringUtils;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String printBoard() {
        var sb = new StringBuilder();
        for (int i = Board.ROWS.length - 1; i >= 0; i--) {
            sb.append(printRank(board.getPiecesWithRow(Board.ROWS[i])));
        }
        return sb.toString();
    }

    private String printRank(List<Piece> pieces) {
        var sb = new StringBuilder();
        for (var piece : pieces) {
            sb.append(piece.getSymbol().getValue());
        }
        return StringUtils.appendNewLine(sb.toString());
    }
}
