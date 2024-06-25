package com.seong.chess;

import com.seong.chess.pieces.Pawn;
import com.seong.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<Piece> pieces = new ArrayList<>();

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public int size() {
        return pieces.size();
    }

    public Piece findPawn(int index) {
        return pieces.stream()
                .filter(piece -> piece instanceof Pawn)
                .toList()
                .get(index);
    }
}
