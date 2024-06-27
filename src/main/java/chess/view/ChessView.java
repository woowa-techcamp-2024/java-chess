package chess.view;

import chess.board.Board;
import chess.board.Rank;
import chess.pieces.Piece;

import java.util.stream.Collectors;

import static chess.utils.StringUtils.NEWLINE;

public class ChessView {

    public String printBoard(Board board) {
        return board.getRanks().stream()
                .map(this::printRank)
                .collect(Collectors.joining(NEWLINE));
    }

    protected String printRank(Rank rank) {
        return rank.getPieces().stream()
                .map(Piece::getRepresentation)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public ChessView() {
    }
}
