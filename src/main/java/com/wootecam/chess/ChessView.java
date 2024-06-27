package com.wootecam.chess;

import static com.wootecam.chess.utils.StringUtils.NEW_LINE;
import static com.wootecam.chess.utils.StringUtils.appendNewLine;

import com.wootecam.chess.pieces.Rank;
import java.util.List;
import java.util.stream.Collectors;

public class ChessView {

    public void printBoard(final List<Rank> ranks) {
        System.out.println(showBoard(ranks));
    }

    private String showBoard(final List<Rank> ranks) {
        return appendNewLine(ranks.stream()
                .map(this::createRankResults)
                .collect(Collectors.joining(NEW_LINE)));
    }

    private String createRankResults(final Rank rank) {
        return rank.createResults();
    }
}
