package chess;

import static utils.StringUtils.appendNewLine;

public class ChessView {

    void printChessBoard(Board board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rank rank : board.getRanks()) {
            stringBuilder.append(appendNewLine(rank.toString()));
        }
        System.out.println(stringBuilder);
    }
}
