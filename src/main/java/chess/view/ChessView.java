package chess.view;

import chess.board.Board;
import chess.board.Rank;

import static utils.StringUtils.appendNewLine;

public class ChessView {

    public String showBoard(Board board) {
        StringBuilder sb = new StringBuilder();

        board.getRanks()
                .stream()
                .map(Rank::toString)
                .forEach(s -> sb.append(appendNewLine(s)));

        return sb.toString();

    }

}
