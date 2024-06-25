package com.wootecam.chess;

import com.wootecam.chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final ChessBoard chessBoard;
    private final List<Pawn> pawns;

    public Board() {
        chessBoard = new ChessBoard();
        this.pawns = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        chessBoard.add(pawn);
        pawns.add(pawn);
    }

    public int size() {
        return pawns.size();
    }

    public Pawn findPawn(int index) {
        if (index < 0 || index >= pawns.size()) {
            throw new IllegalArgumentException("The specified pawn is not found");
        }
        return pawns.get(index);
    }
}
