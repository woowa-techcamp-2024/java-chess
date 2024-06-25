package com.wootecam.chess;

import static com.wootecam.chess.ChessBoard.MAX_COL;

import com.wootecam.chess.pieces.Color;
import com.wootecam.chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private final ChessBoard chessBoard;
    private final List<Pawn> pawns;

    public Board() {
        chessBoard = new ChessBoard();
        this.pawns = new ArrayList<>();
    }

    public void initialize() {
        int whitePawnIndex = MAX_COL;
        for (int i = 0; i < MAX_COL; ++i) {
            Pawn pawn = new Pawn(Color.WHITE);
            add(pawn, whitePawnIndex + i);
        }

        int blackPawnIndex = chessBoard.size() - MAX_COL;
        for (int i = 0; i < MAX_COL; ++i) {
            Pawn pawn = new Pawn(Color.BLACK);
            add(pawn, blackPawnIndex + i);
        }
    }

    public void add(Pawn pawn) {
        chessBoard.add(pawn);
        pawns.add(pawn);
    }

    public void add(Pawn pawn, int index) {
        chessBoard.add(pawn, index);
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

    public String getWhitePawnsResult() {
        return pawns.stream()
                .filter(p -> p.getColor() == Color.WHITE)
                .map(p -> p.getRepresentation().value)
                .collect(Collectors.joining());
    }

    public String getBlackPawnsResult() {
        return pawns.stream()
                .filter(p -> p.getColor() == Color.BLACK)
                .map(p -> p.getRepresentation().value)
                .collect(Collectors.joining());
    }
}
