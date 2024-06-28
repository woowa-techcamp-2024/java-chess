package chess.view;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;

import static chess.utils.StringUtils.appendNewLine;

public class ChessView {

    private static final int BOARD_SIZE = 8;
    private static final String RANK = "a b c d e f g h";
    protected static final String SPACE = " ";

    private ChessView() {
    }

    public static String showBoard(final Board board) {
        StringBuilder chessBoard = new StringBuilder();

        for (int row = BOARD_SIZE; row >= 1; row--) {
            for (int col = 1; col <= BOARD_SIZE; col++) {
                Position position = Position.of(Rank.of(row), File.of(col));
                char representation = board.getPieceInPosition(position);

                chessBoard.append(representation).append(SPACE);
            }
            chessBoard.append(appendNewLine(String.valueOf(Rank.of(row).getIndex())));
        }

        chessBoard.append(RANK);

        return chessBoard.toString();
    }
}
