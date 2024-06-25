package com.seong.chess;

import com.seong.chess.pieces.Piece;
import java.util.ArrayList;
import java.util.List;

public class Rank {

    private final int row;
    private final List<Piece> pieces = new ArrayList<>();

    public Rank(int row) {
        this.row = row;
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieces) {
            sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public int pieceCount() {
        return (int) pieces.stream()
                .filter(Piece::isNotBlank)
                .count();
    }
}
