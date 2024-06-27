package chess.view;

import chess.board.Board;
import utils.StringUtils;

public class ChessView {

    public void print(Board board) {
        System.out.println(showBoard(board));
    }

    public String showBoard(Board board) {
        StringBuilder sb = new StringBuilder();
        for (int rankNum = Board.RANK_COUNT - 1; rankNum >= 0; rankNum--) {
            StringBuilder rank = StringUtils.appendNewLine(board.getRank(rankNum).show());
            sb.append(rank);
        }
        return sb.toString();
    }
}
