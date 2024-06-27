package com.wootecam.chess;

import com.wootecam.chess.board.Board;
import com.wootecam.chess.pieces.Piece;
import com.wootecam.chess.utils.StringUtils;
import java.util.List;
import java.util.stream.Collectors;

public class ChessView {

    public void printChessBoard(Board board) {
        List<List<Piece>> curState = board.getCurrentState();

        String curStateResponse = curState.stream()
                .map(r -> r.stream()
                        .map(Piece::getRepresentation)
                        .map(rp -> rp.value)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(StringUtils.NEW_LINE));

        System.out.println(curStateResponse);
    }
}
