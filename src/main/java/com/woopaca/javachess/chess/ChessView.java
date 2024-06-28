package com.woopaca.javachess.chess;

import com.woopaca.javachess.utils.StringUtils;

import java.util.List;

public class ChessView {

    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    public String showBoard() {
        List<Rank> ranks = board.getRanks();
        StringBuilder boardResult = new StringBuilder();
        for (int i = 0; i < Board.BOARD_SIZE; i++) {
            Rank rank = ranks.get(i);
            String rankResult = rank.generateResult();
            boardResult.append(StringUtils.appendNewLine(rankResult));
        }

        return boardResult.toString();
    }

    public String getWhitePawnsResult() {
        List<Rank> ranks = board.getRanks();
        Rank whitePanwsRank = ranks.get(Board.WHITE_PAWNS_RANK);
        return whitePanwsRank.generateResult();
    }

    public String getBlackPawnsResult() {
        List<Rank> ranks = board.getRanks();
        Rank blackPanwsRank = ranks.get(Board.BLACK_PAWNS_RANK);
        return blackPanwsRank.generateResult();
    }

}
